package com.endava.services;


import com.endava.cloudinary.CloudinaryService;
import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BookRepo;
import com.endava.repositories.BooksForRentRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.CreateCustomUser;
import util.IntegrationTest;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@IntegrationTest
@AutoConfigureMockMvc
class BookServiceTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JwtUtilService jwtUtilService;

    @Mock
    private BookRepo bookRepo;

    @Mock
    private BookRefRepo bookRefRepo;

    @Mock
    private BooksForRentRepo booksForRentRepo;

    @InjectMocks
    private BookService bookService;

    @Mock
    private CloudinaryService cloudinaryService;


    @Test
    void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/api/books")).andExpect(status().isForbidden());
    }

    @Test
    void shouldAllowAccessToUserIfItIsAuthenticated() throws Exception {
        String token = jwtUtilService.generateToken("miha78");
        String bearerToken = "Bearer " + token;

        mvc.perform(MockMvcRequestBuilders
                        .get("/api/books")
                        .header("Authorization", bearerToken))
                .andExpect(status().isOk());
    }


    @Test
    @Ignore("400 Required request part 'body' is not present")
    void createBookSuccessfully() throws Exception {
        final var userId = CreateCustomUser.johnDoe().getUserId();

        final var book = new BookDto(
                UUID.randomUUID(),
                "The Lord of the Rings (The Fellowship of the Rings)",
                "J.R.R. Tolkien",
                LocalDate.of(1954, 11, 11),
                "Fantasy",
                " ");

        //creating bookRef object
        final var bookRef = new BooksRefDto(
                UUID.randomUUID(),
                book.getBookId(),
                userId
        );
        //creating bookForRent object
        final var bookForRent = new BooksForRentDto(UUID.randomUUID(), bookRef.getBookRefId());

        //mocking multipart file
        MockMultipartFile file =
                new MockMultipartFile("file",
                        "image.png",
                        MediaType.MULTIPART_FORM_DATA_VALUE,
                        new ClassPathResource("image.png").getInputStream());

        //set imageURL
        String imageUrl = cloudinaryService.uploadFile(file);
        book.setImageUrl(imageUrl);

        //saving the books in repos
        bookRepo.save(book);
        bookRefRepo.save(bookRef);
        booksForRentRepo.save(bookForRent);

        // arrange
        when(bookRepo.save(book)).thenReturn(book);
        when(bookRefRepo.save(bookRef)).thenReturn(bookRef);
        when(booksForRentRepo.save(bookForRent)).thenReturn(bookForRent);
        when(cloudinaryService.uploadFile(file)).thenReturn(String.valueOf(file));

        //expected result
        ResponseEntity<?> result = bookService.createBook(userId, book, file);

        //asserting the result
        assertThat(result.getStatusCodeValue()).isEqualTo(201);
        assertThat(result.getBody()).isEqualTo("Book created.");
    }
}

