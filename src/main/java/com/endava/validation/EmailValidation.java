package com.endava.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.endava.utils.Constants.EMAIL_PATTERN;

public class EmailValidation {

    public static boolean isValidEmailAddress(String email) {
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
