package com.myth.utils;

import com.myth.constant.App;
import com.password4j.Password;

import java.security.SecureRandom;

public class PasswordUtils {

    public static String hash(String raw, String salt) {
        return Password.hash(raw).addSalt(salt).withArgon2().getResult();
    }

    public static boolean verify(String password, String salt, String hash) {
        return Password.check(password, hash).addSalt(salt).withArgon2();
    }

    public static String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[App.PASSWORD_SALT_LENGTH];
        secureRandom.nextBytes(salt);
        return salt.toString();
    }
}
