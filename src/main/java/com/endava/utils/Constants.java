package com.endava.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Constants {
    public static long EXPIRE_DURATION = 600000;
    public static String SECRET_KEY = "secret";

    public static  LocalDate DEFAULT_RENT_VALUE = LocalDate.now().plusDays(1);
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");



    //TODO : add constants for the rest of the application
    public static String ACCESS_TOKEN_HEADER = "Authorization";
    public static String ACCESS_TOKEN_PREFIX = "Bearer ";
}
