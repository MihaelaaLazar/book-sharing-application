package com.endava.services;

;
import com.endava.repositories.BooksForRentRepo;
import com.endava.repositories.RentedBooksRepo;
import com.endava.utils.RentalPeriod;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import util.CreateCustomBook;
import util.CreateCustomUser;
import util.IntegrationTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


@IntegrationTest
class RentBookTest {

    @Mock
    private RentedBooksRepo rentedBooksRepo;

    @Mock
    private BooksForRentRepo booksForRentRepo;

    @InjectMocks
    private BooksForRentService booksForRentService;

    @Test
    void shouldRentBook() {
        final var userId = CreateCustomUser.johnDoe().getUserId();
        final var rentalPeriod = RentalPeriod.ONE_WEEK;
        final var bookForRent = CreateCustomBook.createBookForRent();

        when(booksForRentRepo.findByBookRefId(bookForRent.getBookRef().getBookRefId())).thenReturn(Optional.of(bookForRent));

        final var rentedBook =
                booksForRentService.rentBook(userId, bookForRent.getBookRef().getBookRefId(), rentalPeriod);

        assertThat(rentedBook.getStatusCodeValue()).isEqualTo(200);
        assertThat(rentedBook.getBody()).isEqualTo("Book rented");

    }

    @Test
    void shouldReturnNoFoundIfTheBookIsntAvailable(){
        final var bookRefId = UUID.randomUUID();

        when(booksForRentRepo.findByBookRefId(bookRefId)).thenReturn(Optional.empty());

        final var rentedBook = booksForRentService.rentBook(UUID.randomUUID(), bookRefId, RentalPeriod.ONE_WEEK);

        assertThat(rentedBook.getStatusCodeValue()).isEqualTo(404);
        assertThat(rentedBook.getBody()).isEqualTo("Book not available");

    }


}
