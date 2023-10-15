package edu.hw2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("uncommentedmain")

public class Task4 {

    private final static Logger LOGGER = LogManager.getLogger();

    private Task4() {

    }

    public static void main(String[] args) {
        LOGGER.info(callingInfo());
    }

    public static CallingInfo callingInfo() {
        String className;
        String  methodName;
        try {
            throw new Exception();
        } catch (Throwable e) {

            StackTraceElement[] stktrace = e.getStackTrace();
            String str = stktrace[1].toString();
            String str1 = str.substring(0, str.indexOf('('));
            int splitIndex = str1.lastIndexOf('.');
            className = str1.substring(0, splitIndex);
            methodName = str1.substring(splitIndex + 1);

        }
        return new CallingInfo(className, methodName);
    }

    public record CallingInfo(String className, String methodName) {
    }
}
