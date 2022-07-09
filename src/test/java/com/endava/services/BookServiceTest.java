package com.endava.services;

import com.endava.models.BookDto;
import com.endava.models.BooksForRentDto;
import com.endava.models.BooksRefDto;
import com.endava.repositories.BookRefRepo;
import com.endava.repositories.BookRepo;
import com.endava.repositories.BooksForRentRepo;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    private static final UUID BOOK_ID = UUID.randomUUID();
    private static final BookDto BOOK_DTO = new BookDto();
    private static final String TITLE = "title";
    private static final String AUTHOR = "author";

    private static final String DESCRIPTION = "description";

    private static final LocalDate PUBLISHED_DATE = LocalDate.now();

    @Mock
    private BookRepo bookRepo;

    @Mock
    private BookRefRepo bookRefRepo;

    @Mock
    private BooksForRentRepo booksForRentRepo;


    @InjectMocks
    private BookService bookService;

    @BeforeEach
    public void beforeEach() {
        BOOK_DTO.setBookId(BOOK_ID);
        BOOK_DTO.setTitle(TITLE);
        BOOK_DTO.setAuthor(AUTHOR);
        BOOK_DTO.setDescription(DESCRIPTION);
        BOOK_DTO.setDateOfPublication(PUBLISHED_DATE);
    }


    @Test
    public void shouldGetAllBooks() {
        when(bookRepo.findAll()).thenReturn(List.of(BOOK_DTO));

        List<BookDto> books = bookService.getAllBooks();

        assertThat(books).isNotNull();
    }
    @Test
    public void shouldGetBooksByUserId(){
        when(bookRefRepo.findByUserUserId(BOOK_ID)).thenReturn(List.of(new BooksRefDto()));

        Stream<Object> booksRefDto = bookService.getBooksByUserId(BOOK_ID);

        assertThat(booksRefDto).isNotNull();
    }

    @Test
    public void shouldGetBooksByTitleOrAuthor(){
        when(bookRepo.findByTitleOrAuthor(Optional.of(TITLE), Optional.of(AUTHOR))).thenReturn(List.of(BOOK_DTO));

        Stream<?> books = bookService.getBooksByTitleOrAuthor(Optional.of(TITLE), Optional.of(AUTHOR));

        assertThat(books).isNotNull();
    }

    @Test
    @Ignore
    public void shouldCreateBook(){
        when(bookRepo.save(BOOK_DTO)).thenReturn(BOOK_DTO);
        when(bookRefRepo.save(new BooksRefDto(null, BOOK_ID, BOOK_ID))).thenReturn(new BooksRefDto(null, BOOK_ID, BOOK_ID));
        when(booksForRentRepo.save(new BooksForRentDto(null, new BooksRefDto(null, BOOK_ID, BOOK_ID)))).thenReturn(new BooksForRentDto(null, new BooksRefDto(null, BOOK_ID, BOOK_ID)));

        bookService.createBook(BOOK_ID, BOOK_DTO);

        assertThat(BOOK_DTO).isNotNull();
    }

}
