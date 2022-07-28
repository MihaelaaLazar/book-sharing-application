package com.endava.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Constants {
    public static final long EXPIRE_DURATION = 3600000;

    public static final String SECRET_KEY = "secret";

    public static final LocalDate DEFAULT_RENT_VALUE = LocalDate.now().plusDays(1);

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm:ss");

    public static final String EMAIL_PATTERN =
            "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";

    public static final String ACCESS_TOKEN_HEADER = "Authorization";

    public static final String ACCESS_TOKEN_PREFIX = "Bearer ";

    public static final String SECURITY_SCHEME = "bearer";

}
