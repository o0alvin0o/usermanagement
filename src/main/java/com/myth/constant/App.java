package com.myth.constant;

public class App {
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    public static final String USERNAME_REGEX = "^[A-Za-z0-9]{5,15}$";
    public static final int PASSWORD_SALT_LENGTH = 16;
}
