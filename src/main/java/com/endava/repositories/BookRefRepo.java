package com.endava.repositories;

import com.endava.models.BooksRefDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRefRepo extends CrudRepository<BooksRefDto, UUID> {
    List<BooksRefDto> findByUserUserId(UUID userId);

    @Query("SELECT b FROM BooksRefDto b WHERE b.user.userId = ?1 ")
    List<BooksRefDto> findAllByUserId(UUID userId);

    @Query("SELECT b from BooksRefDto b WHERE b.book.bookId = ?1")
    BooksRefDto findByBookId(UUID bookId);
}
