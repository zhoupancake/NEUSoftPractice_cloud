package com.system.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * SHA256 util class to encrypt
 */
public class SHA256Util {

    public static String encrypt(String input) {
        try {
            // 获取SHA-256消息摘要算法对象
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // 使用输入字符串进行更新
            digest.update(input.getBytes(StandardCharsets.UTF_8));

            // 获取哈希结果并转换为十六进制字符串
            byte[] hashBytes = digest.digest();
            return String.format("%064x", new BigInteger(1, hashBytes));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        String input = "123456";
        String encrypted = encrypt(input);
        System.out.println("Encrypted: " + encrypted);
        String check = new Scanner(System.in).nextLine();
        if (encrypted.equals(check)) {
            System.out.println("Correct");
        } else {
            System.out.println("Wrong");
        }
    }
}