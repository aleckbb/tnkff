package edu.hw10.Task2;

import edu.hw10.Task2.Palindrome.Palindrome;
import edu.hw10.Task2.Palindrome.PalindromeImp;
import edu.hw10.Task2.Fib.FibCalculator;
import edu.hw10.Task2.Fib.FibCalculatorImp;
import java.util.Arrays;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task2Test {

    @Test
    @DisplayName("Кеширующий прокси работает для fib")
    void test1() {
        // given
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());

        // when
        long result = proxyFib.fib(5);
        Map<String, Object[]> cache = CacheProxy.getCache();

        // then
        assertEquals(5, result);
        assertNotNull(cache);
        assertEquals("[[5]]", Arrays.toString(cache.keySet().toArray()));
        assertEquals("[5, fib]", Arrays.toString(cache.get(Arrays.toString(new Object[] {5}))));
    }

    @Test
    @DisplayName("Кеширующий прокси работает для isPalindrome")
    void test2() {
        // given
        Palindrome palindrome = new PalindromeImp();
        Palindrome proxyPalindrome = (Palindrome) CacheProxy.create(palindrome, palindrome.getClass());

        // when
        boolean result1 = proxyPalindrome.isPalindrome("12321");
        boolean result2 = proxyPalindrome.isPalindrome("12345");
        Map<String, Object[]> cache = CacheProxy.getCache();

        // then
        assertTrue(result1);
        assertFalse(result2);
        assertNotNull(cache);
        assertEquals("[[12345], [12321]]", Arrays.toString(cache.keySet().toArray()));
        assertEquals("[true, isPalindrome]", Arrays.toString(cache.get(Arrays.toString(new Object[] {"12321"}))));
    }

    @Test
    @DisplayName("Сохраняются только уникальные значения")
    void test3() {
        // given
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());

        // when
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        proxyFib.fib(5);
        Map<String, Object[]> cache = CacheProxy.getCache();

        // then
        assertNotNull(cache);
        assertEquals(1, cache.size());
    }

    @Test
    @DisplayName("Сохраняются только уникальные значения")
    void test4() {
        // given
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());

        // when
        proxyFib.fib(5);
        proxyFib.fib(1);
        proxyFib.fib(2);
        proxyFib.fib(3);
        proxyFib.fib(4);
        Map<String, Object[]> cache = CacheProxy.getCache();

        // then
        assertNotNull(cache);
        assertEquals(5, cache.size());
    }
}
