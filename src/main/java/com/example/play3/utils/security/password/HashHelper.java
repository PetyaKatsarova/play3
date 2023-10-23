package com.example.play3.utils.security.password;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashHelper {

    public static final String  SHA_256 = "SHA-256";
    private static final int    HEX_BASE = 16;

    public static String hash(String password) throws NoSuchAlgorithmException {
        String noSalt = "";
        return hash(password, noSalt);
    }

    public static String hash(String password, String salt) throws NoSuchAlgorithmException {
        String noPepper = "";
        return hash(password, salt, noPepper);
    }

    public static String hash(String password, String salt, String pepper) throws NoSuchAlgorithmException {
        String pepper1 = "";
        String pepper2 = pepper;
        if (pepper.length() >= 2) {
            pepper1 = pepper.substring(0, (pepper.length() / 2));
            pepper2 = pepper.substring((pepper.length() / 2));
        }
        byte[] digest = getPassBytes(salt, pepper1, password, pepper2);
        return encodeHexString(digest);
    }

    //returns an array of bytes( unique cryptographic hash of the combined salt, pepper1, password, and pepper2 using the SHA-256 algorithm)
    private static byte[] getPassBytes(String salt, String pepper1, String password, String pepper2) throws NoSuchAlgorithmException {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));
        md.update(pepper1.getBytes(StandardCharsets.UTF_8));
        md.update(password.getBytes(StandardCharsets.UTF_8));
        md.update(pepper2.getBytes(StandardCharsets.UTF_8));
        return md.digest();
    }

    private static MessageDigest getMessageDigest() throws NoSuchAlgorithmException {
        return MessageDigest.getInstance(HashHelper.SHA_256);
    }

    public static String encodeHexString(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
        for (byte b : byteArray) {
            hexStringBuffer.append(byteToHex(b));
        }
        return hexStringBuffer.toString();
    }

    private static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, HEX_BASE);
        hexDigits[1] = Character.forDigit((num & 0xF), HEX_BASE);
        return new String(hexDigits);
    }
}
