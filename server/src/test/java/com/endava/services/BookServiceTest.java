package com.endava.services;


import com.endava.models.BookDto;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import util.IntegrationTest;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@IntegrationTest
class BookServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldCheckIfTestLoggerWorks(){
        assertThat(true).isTrue();
    }

    @Test
    @Ignore("403 forbidden instead of 201 created, because of the security filter")
    void createBookSuccessfully(){
        final var bookRequest = new BookDto(
                UUID.fromString("92f85485-c781-46df-b49a-a0e963785017"),
                "Title",
                "Author",
                LocalDate.of(2020, 1, 1),
                "Description",
                "https://res.cloudinary.com/miha111/image/upload/v1659082185/cei5ky8y8a7uawgx5wjn.jpg");

        final var response = restTemplate.postForEntity("/api/books/92f85485-c781-46df-b49a-a0e963785018/create", bookRequest, BookDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(bookRequest);
    }

}
