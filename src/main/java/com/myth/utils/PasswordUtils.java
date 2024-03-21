package com.myth.utils;

import io.quarkus.elytron.security.common.BcryptUtil;

public class PasswordUtils {

    private static final int ITERATION = 4;

    public static String hash(String raw) {
        return BcryptUtil.bcryptHash(raw, ITERATION);
    }
}
