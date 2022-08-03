package com.endava.repositories;

import com.endava.models.BookDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface BookRepo extends CrudRepository<BookDto, UUID> {

    @Query("SELECT b FROM BookDto b WHERE LOWER(b.title) LIKE CONCAT('%',LOWER(?1), '%') OR LOWER(b.author) LIKE CONCAT('%', LOWER(?1), '%')")
    List<BookDto> findByQuery(String query);

    @Query("SELECT b FROM BookDto b WHERE LOWER(b.title) LIKE CONCAT('%',LOWER(?1), '%') OR LOWER(b.author) LIKE CONCAT('%', LOWER(?1), '%')")
    Set<BookDto> _findByQuery(String query);

    BookDto findByBookId(UUID bookId);

    Optional<BookDto> findByTitle(String title);

    List<BookDto> findAll();

    Page<BookDto> findAll(Pageable pageable);

    @Query("SELECT COUNT(b) FROM BookDto b")
    long countByBookId();
}

