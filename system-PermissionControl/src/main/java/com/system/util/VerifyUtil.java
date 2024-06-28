package com.system.util;

/**
 * The verify util class to verify the encrypted keys.
 * This class is designed for the verification of the encrypted keys from the gateway.
 */
public class VerifyUtil {

    private static String encrypt(String input){
        String encryptedKeys = "6be629bc3fc86d8d";
        return SHA256Util.encrypt(input+encryptedKeys);
    }

    public static boolean verify(String result, String encryptedKeys){
        if(result == null)
            return false;
        return encrypt(encryptedKeys).equals(result);
    }

}
