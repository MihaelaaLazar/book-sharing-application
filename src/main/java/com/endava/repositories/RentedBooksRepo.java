package com.endava.repositories;

import com.endava.models.RentedBooksDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RentedBooksRepo extends CrudRepository<RentedBooksDto, UUID> {
    @Query("SELECT b FROM RentedBooksDto b JOIN BooksRefDto br ON br.bookRefId = b.booksRefDto.bookRefId join UserDto u on br.user.userId = u.userId  WHERE u.userId = ?1")
    List<RentedBooksDto> findBookByUserId(UUID userId);

    RentedBooksDto findByRentedBookId(UUID rentedBookId);

    @Query("Select b from RentedBooksDto b where b.user.userId = ?1")
    List<RentedBooksDto> findRentedBookByUserId(UUID userId);

}
