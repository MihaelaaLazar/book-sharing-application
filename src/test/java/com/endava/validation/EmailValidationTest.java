package com.endava.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
public class EmailValidationTest {
    private static final String VALID_EMAIL_ADDRESS = "example@gmail.com";
    private static final String INVALID_EMAIL_ADDRESS = "examplegmail.com";


    @InjectMocks
    private EmailValidation emailValidation;

    @Test
    public void shouldReturnTrueWhenEmailIsValid() {
        assertThat(emailValidation.isValidEmailAddress(VALID_EMAIL_ADDRESS)).isTrue();
    }

    @Test
    public void shouldReturnFalseWhenEmailIsInvalid() {
        assertThat(emailValidation.isValidEmailAddress(INVALID_EMAIL_ADDRESS)).isFalse();
    }
}
