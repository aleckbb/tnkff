package edu.hw3.Task4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashMap;

@SuppressWarnings("uncommentedmain")
public class Task4 {
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

    private Task4(){

    }

    private static void setHashMap(){
        hashMap.put(ONE, "I");
        hashMap.put(FIVE, "V");
        hashMap.put(TEN, "X");
        hashMap.put(FIVETEEN, "L");
        hashMap.put(HUNDRED, "C");
        hashMap.put(FIVE_HUNDRED, "D");
        hashMap.put(THOUSAND, "M");
    }

public static String TENS(Integer num, Integer CONST, Integer CONST_PREV){
    String res = "";
    if(num >= CONST_PREV - CONST && num < THOUSAND){
        return hashMap.get(CONST) + hashMap.get(CONST_PREV);
    }
    else {
        for (int i = 0; i < num / CONST; ++i) {
            res += hashMap.get(CONST);
        }
        return res;
    }
}

    public static String convertToRoman(Integer num){
        String res = "";
        String str = "";
        if(num >= ONE && num < THOUSAND*FOUR){
            if(num >= THOUSAND){
                res += TENS(num, THOUSAND, ONE);
                num %= THOUSAND;
            }
            if(num >= FIVE_HUNDRED){
                if(num >= THOUSAND - HUNDRED){
                    res += hashMap.get(HUNDRED) + hashMap.get(THOUSAND);
                    num %= HUNDRED;
                }
                else{
                    res += hashMap.get(FIVE_HUNDRED);
                    num %= FIVE_HUNDRED;
                }
            }
            if(num >= HUNDRED){
                str = TENS(num, HUNDRED, FIVE_HUNDRED);
                if(str.equals(hashMap.get(FIVE_HUNDRED - HUNDRED))){
                    res += hashMap.get(HUNDRED) + hashMap.get(FIVE_HUNDRED);
                    num %= HUNDRED;
                }
                else {
                    tmp = num / HUNDRED;
                    num %= HUNDRED;
                    for (int i = 0; i < tmp; ++i) {
                        res += hashMap.get(HUNDRED);
                    }
                }
            }
            if(num >= FIVETEEN){
                if(num >= HUNDRED - TEN){
                    res += hashMap.get(TEN) + hashMap.get(HUNDRED);
                    num %= TEN;
                }
                else{
                    res += hashMap.get(FIVETEEN);
                    num %= FIVETEEN;
                }
            }
            if(num >= TEN){
                if(num >= FIVETEEN - TEN){
                    res += hashMap.get(TEN) + hashMap.get(FIVETEEN);
                    num %= TEN;
                }
                else {
                    tmp = num / TEN;
                    num %= TEN;
                    for (int i = 0; i < tmp; ++i) {
                        res += hashMap.get(TEN);
                    }
                }
            }
            if(num >= FIVE){
                if(num >= TEN - ONE){
                    res += hashMap.get(ONE) + hashMap.get(TEN);
                    num %= ONE;
                }
                else{
                    res += hashMap.get(FIVE);
                    num %= FIVE;
                }
            }
            if(num >= ONE){
                if(num >= FIVE - ONE){
                    res += hashMap.get(ONE) + hashMap.get(FIVE);
                    num %= ONE;
                }
                else {
                    tmp = num / ONE;
                    num %= ONE;
                    for (int i = 0; i < tmp; ++i) {
                        res += hashMap.get(ONE);
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        setHashMap();
        LOGGER.info(convertToRoman(39));
    }
}
