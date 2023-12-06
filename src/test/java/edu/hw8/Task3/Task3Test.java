package edu.hw8.Task3;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3Test {

    private final Path path = Path.of("src/test/java/edu/hw8/Task3/Database/database.txt");

    @Test
    @DisplayName("Считывание с файла делается корректно")
    void test1() {
        // given
        String expected =
            "{8775ce1c0dd8f08f50951700f81636e7=a.v.petrov," +
                " b4b147bc522828731f1a016bfa72c073=a.s.ivanov," +
                " 3b770ebe9b04f171f0ead0e07d8e2882=v.v.belov," +
                " c4ca4238a0b923820dcc509a6f75849b=k.p.maslov}";

        // when
        ReadDatabase.readInMap(path);

        // then
        assertEquals(expected, Maps.passwordsFromDatabase.toString());
    }

    @Test
    @DisplayName("Перебиратор паролей перебирает все возможные пароли")
    void test2() {
        // given
        int expected = 3906;
        List<String> allPasswordsTwoSign = new ArrayList<>();

        // when
        for (int i = 1; i < 3; i++) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(i);
            String password = passwordGenerator.nextPassword();
            while (!password.isEmpty()) {
                allPasswordsTwoSign.add(password);
                password = passwordGenerator.nextPassword();
            }
        }

        // then
        assertEquals(expected, allPasswordsTwoSign.size());
        assertEquals("0", allPasswordsTwoSign.getFirst());
        assertEquals("zz", allPasswordsTwoSign.getLast());
    }

    @Test
    @DisplayName("MD5 хэш вычисляется верно")
    void test3() {
        // given
        String s = "1234";
        String expected = "81dc9bdb52d04dc20036dbd8313ed055";

        // then
        assertEquals(expected, HashGenerator.getMD5Hash(s));
    }

    @Test
    @DisplayName("Программа находит все пароли пользователей(несколько потоков)")
    void test4() throws ExecutionException, InterruptedException {
        // given
        String expected = "{a.v.petrov=1z, v.v.belov=z1, a.s.ivanov=00, k.p.maslov=1}";

        // then
        ReadDatabase.readInMap(path);
        ExecutorService threads = Executors.newFixedThreadPool(2);
        var tasks = IntStream.range(1, 3)
            .mapToObj(passwordLength -> CompletableFuture.runAsync(() -> {
                PasswordGenerator passwordGenerator = new PasswordGenerator(passwordLength);
                String password = passwordGenerator.nextPassword();
                while (!Maps.passwordsFromDatabase.isEmpty() && !password.isEmpty()) {
                    String hash = HashGenerator.getMD5Hash(password);
                    if (Maps.passwordsFromDatabase.containsKey(hash)) {
                        Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
                    }
                    password = passwordGenerator.nextPassword();
                }
            }, threads))
            .limit(2)
            .toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
        threads.shutdown();

        // then
        assertEquals(expected, Maps.passwordsOfClients.toString());
    }

    @Test
    @DisplayName("Программа находит все пароли пользователей(один поток)")
    void test5() {
        // given
        String expected = "{a.v.petrov=1z, v.v.belov=z1, a.s.ivanov=00, k.p.maslov=1}";

        // then
        ReadDatabase.readInMap(path);
        for (int i = 1; i < 3; i++) {
            PasswordGenerator passwordGenerator = new PasswordGenerator(i);
            String password = passwordGenerator.nextPassword();
            while (!Maps.passwordsFromDatabase.isEmpty() && !password.isEmpty()) {
                String hash = HashGenerator.getMD5Hash(password);
                if (Maps.passwordsFromDatabase.containsKey(hash)) {
                    Maps.passwordsOfClients.put(Maps.passwordsFromDatabase.remove(hash), password);
                }
                password = passwordGenerator.nextPassword();
            }
        }

        // then
        assertEquals(expected, Maps.passwordsOfClients.toString());
    }
}
