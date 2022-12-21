package com.avaya.smgr.tm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class test {

    public static String getSecurePassword(String password, byte[] salt) {

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        System.out.println(" SALT -> " + String.valueOf(salt));
        return salt;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        // same salt should be passed
        byte[] salt = getSalt();
        String password1 = getSecurePassword("TEST", salt);
        String password2 = getSecurePassword("Test", salt);
        System.out.println(" Password 1 -> " + password1);
        System.out.println(" Password 2 -> " + password2);
        System.out.println(" SALT -> " + String.valueOf(salt));
        if (password1.equals(password2)) {
            System.out.println("passwords are equal");
        }
    }
}

//c55a19b5aff6a76b325b2cde3281853f209567d9f8e92ff2e811b7e4192fc74e
//[B@3498ed