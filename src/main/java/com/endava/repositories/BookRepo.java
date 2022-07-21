package com.endava.repositories;

import com.endava.models.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BookRepo extends CrudRepository<BookDto, UUID> {

    @Query("SELECT b FROM BookDto b WHERE b.title LIKE CONCAT('%',?1, '%') OR b.author LIKE CONCAT('%', ?2, '%')")
    List<BookDto> findByTitleOrAuthor(Optional<String> title, Optional<String> author);

    BookDto findByBookId(UUID bookId);

    Optional<BookDto> findByTitle(String title);

    List<BookDto> findAll();

    Page<BookDto> findAll(Pageable pageable);

    @Query("SELECT COUNT(b) FROM BookDto b")
    long countByBookId();
}

