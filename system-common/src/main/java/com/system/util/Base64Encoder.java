package com.system.util;
import java.util.Base64;

public class Base64Encoder {
    public static String encode(String text) {
        if (text == null) {
            return null;
        }
        return Base64.getEncoder().encodeToString(text.getBytes());
    }

    public static String decode(String encodedText) {
        if (encodedText == null) {
            return null;
        }
        return new String(Base64.getDecoder().decode(encodedText));
    }
}
