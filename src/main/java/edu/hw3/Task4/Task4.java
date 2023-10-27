package edu.hw3.Task4;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")
public class Task4 {
    private static final Integer ZERO = 0;
    private static final Integer ONE = 1;
    private static final Integer FOUR = 4;
    private static final Integer FIVE = 5;
    private static final Integer TEN = 10;
    private static final Integer FIVETEEN = 50;
    private static final Integer HUNDRED = 100;
    private static final Integer FIVE_HUNDRED = 500;
    private static final Integer THOUSAND = 1000;
    private static HashMap<Integer, String> hashMap = new HashMap<>();

    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {

    }

    private static void setHashMap() {
        hashMap.put(ONE, "I");
        hashMap.put(FIVE, "V");
        hashMap.put(TEN, "X");
        hashMap.put(FIVETEEN, "L");
        hashMap.put(HUNDRED, "C");
        hashMap.put(FIVE_HUNDRED, "D");
        hashMap.put(THOUSAND, "M");
    }

    public static String ones(Integer num, Integer constant, Integer constPrev) {
        String res = "";
        if (num >= constPrev - constant && num < THOUSAND) {
            return hashMap.get(constant) + hashMap.get(constPrev);
        } else {
            for (int i = ZERO; i < num / constant; ++i) {
                res += hashMap.get(constant);
            }
            return res;
        }
    }

    public static List<Serializable> fives(Integer num, Integer constant, Integer constPrev, Integer constNext) {
        String res = "";
        Integer tmp = num;
        if (num >= constPrev - constNext) {
            res += hashMap.get(constNext) + hashMap.get(constPrev);
            tmp %= constNext;
        } else {
            res += hashMap.get(constant);
            tmp %= constant;
        }
        return Arrays.asList(res, tmp);
    }

    public static String convertToRoman(Integer num) {
        String res = "";
        List<Serializable> list;
        Integer newNum = num;
        if (newNum >= ONE && newNum < THOUSAND * FOUR) {
            if (newNum >= THOUSAND) {
                res += ones(newNum, THOUSAND, ONE);
                newNum %= THOUSAND;
            }

            if (newNum >= FIVE_HUNDRED) {
                list = fives(newNum, FIVE_HUNDRED, THOUSAND, HUNDRED);
                res += list.get(ZERO);
                newNum = (Integer) list.get(ONE);
            }

            if (newNum >= HUNDRED) {
                res += ones(newNum, HUNDRED, FIVE_HUNDRED);
                newNum %= HUNDRED;
            }

            if (newNum >= FIVETEEN) {
                list = fives(newNum, FIVETEEN, HUNDRED, TEN);
                res += list.get(ZERO);
                newNum = (Integer) list.get(ONE);
            }

            if (newNum >= TEN) {
                res += ones(newNum, TEN, FIVETEEN);
                newNum %= TEN;
            }

            if (newNum >= FIVE) {
                list = fives(newNum, FIVE, TEN, ONE);
                res += list.get(ZERO);
                newNum = (Integer) list.get(ONE);
            }

            if (newNum >= ONE) {
                res += ones(newNum, ONE, FIVE);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        setHashMap();
        LOGGER.info(convertToRoman(THOUSAND));
    }
}
