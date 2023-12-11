package edu.hw10.Task2;

import edu.hw10.Task2.Fact.FactCalculator;
import edu.hw10.Task2.Fact.FactCalculatorImp;
import edu.hw10.Task2.Fib.FibCalculator;
import edu.hw10.Task2.Fib.FibCalculatorImp;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Task2Test {

    @Test
    @DisplayName("Прокси хэширует возвращаемое значение метода fib")
    void test1() {
        // given
        FibCalculator fib = new FibCalculatorImp();
        FibCalculator proxyFib = (FibCalculator) CacheProxy.create(fib, fib.getClass());

        // when
        long result = proxyFib.fib(5);
        Map<String, String> hashReturnsValuesOfMethods = CacheProxy.getHashReturnsValuesOfMethods();

        // then
        assertEquals(5, result);
        assertNotNull(hashReturnsValuesOfMethods);
        assertEquals("[e4da3b7fbbce2345d7772b0674a318d5]", hashReturnsValuesOfMethods.keySet().toString());
        assertEquals("fib", hashReturnsValuesOfMethods.get("e4da3b7fbbce2345d7772b0674a318d5"));
    }

    @Test
    @DisplayName("Прокси хэширует возвращаемое значение метода fact")
    void test2() {
        // given
        FactCalculator fib = new FactCalculatorImp();
        FactCalculator proxyFib = (FactCalculator) CacheProxy.create(fib, fib.getClass());

        // when
        long result = proxyFib.fact(5);
        Map<String, String> hashReturnsValuesOfMethods = CacheProxy.getHashReturnsValuesOfMethods();

        // then
        assertEquals(120, result);
        assertNotNull(hashReturnsValuesOfMethods);
        assertEquals("[da4fb5c6e93e74d3df8527599fa62642]", hashReturnsValuesOfMethods.keySet().toString());
        assertEquals("fact", hashReturnsValuesOfMethods.get("da4fb5c6e93e74d3df8527599fa62642"));
    }
}
