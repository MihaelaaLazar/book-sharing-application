package com.endava.repositories;

import com.endava.models.RentedBooksDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentedBooksRepo extends CrudRepository<RentedBooksDto, UUID> {
    List<RentedBooksDto> findAll();

    RentedBooksDto findByRentedBookId(UUID rentedBookId);

    @Query("SELECT b FROM RentedBooksDto b JOIN BooksRefDto br ON br.bookRefId = b.booksRefDto.bookRefId JOIN UserDto u ON br.user.userId = u.userId WHERE u.userId = ?1")
    List<RentedBooksDto> findBookByUserId(UUID userId);

    @Query("SELECT b FROM RentedBooksDto b WHERE b.user.userId = ?1")
    List<RentedBooksDto> findRentedBookByUserId(UUID userId);

    @Query("SELECT b FROM RentedBooksDto b WHERE b.booksRefDto.book.bookId = ?1")
    RentedBooksDto findOneBookByBookId(UUID bookId);

}
