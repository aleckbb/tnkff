package edu.hw11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task2Test {

    public class ArithmeticUtils {
        public int sum(int a, int b) {
            return a + b;
        }
    }

    public class ArithmeticUtilsWithSumAsMulti {
        public int sum(int a, int b) {
            return a * b;
        }
    }

    @Test
    @DisplayName("Замена знака '+' на '*' в функции sum")
    void test1() {
        ByteBuddyAgent.install();
        ArithmeticUtils arithmeticUtils = new ArithmeticUtils();
        new ByteBuddy()
            .redefine(ArithmeticUtilsWithSumAsMulti.class)
            .name(ArithmeticUtils.class.getName())
            .make()
            .load(ArithmeticUtils.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());

        assertNotNull(arithmeticUtils);
        assertEquals(25, arithmeticUtils.sum(5, 5));
    }
}
