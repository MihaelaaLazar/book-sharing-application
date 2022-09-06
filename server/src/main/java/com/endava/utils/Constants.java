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

    public static final String[] SWAGGER_ROUTES = {"/swagger-ui/index.html/**",
            "/swagger-ui/swagger-initializer.js",
            "/swagger-ui/index.css",
            "/api-docs",
            "/swagger-ui/swagger-ui.css",
            "/swagger-ui/swagger-ui-bundle.js",
            "/swagger-ui/swagger-ui-standalone-preset.js",
            "/swagger-ui/favicon-32x32.png",
            "/swagger-ui/favicon-16x16.png",
            "/api-docs/swagger-config"
    };
    public static final String[] USER_ROUTES = {
            "/api/users/register",
            "/api/users/confirm?**",
            "/api/users/login",
            "/api/users/verify/**"
    };

    public static final String ACTUATOR_ROUTES = "/actuator/**";



}
