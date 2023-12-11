package edu.hw10.Task2;

import edu.hw10.Task2.Annotation.Cache;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigInteger;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class CacheProxy implements InvocationHandler {

    private Object object1;

    private static Map<String, String> hashReturnsValuesOfMethods;

    CacheProxy(Object object) {
        this.object1 = object;
        hashReturnsValuesOfMethods = new HashMap<>();
    }

    public static Map<String, String> getHashReturnsValuesOfMethods() {
        return hashReturnsValuesOfMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        var result = method.invoke(object1, args);
        if (result != null) {
            String hash = getMD5Hash(result.toString());
            hashReturnsValuesOfMethods.putIfAbsent(hash, method.getName());
        }
        if (method.isAnnotationPresent(Cache.class) && method.getAnnotation(Cache.class).persist()) {
            File tempFile =
                File.createTempFile("temp_", ".txt", Path.of("src/main/java/edu/hw10/Task2").toFile());
            tempFile.deleteOnExit();
            try (
                BufferedWriter bufferedWriter =
                    new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile)))) {
                for (var key : hashReturnsValuesOfMethods.keySet()) {
                    bufferedWriter.write(key + " - " + hashReturnsValuesOfMethods.get(key) + "\n");
                }
            }
        }
        return result;
    }

    @SuppressWarnings("MagicNumber")
    public static String getMD5Hash(String result) {
        MessageDigest messageDigest;
        byte[] digest;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(result.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        BigInteger bigInt = new BigInteger(1, digest);
        String md5Hex = bigInt.toString(16);

        while (md5Hex.length() < 32) {
            md5Hex = "0" + md5Hex;
        }
        return md5Hex;
    }

    public static Object create(Object object, Class<?> className) {
        ClassLoader objectClassLoader = className.getClassLoader();
        Class[] interfaces = className.getInterfaces();
        Object proxyObject =
            Proxy.newProxyInstance(objectClassLoader, interfaces, new CacheProxy(object));
        return proxyObject;
    }

}
