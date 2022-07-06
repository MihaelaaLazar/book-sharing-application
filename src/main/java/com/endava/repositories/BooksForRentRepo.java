package com.endava.repositories;


import com.endava.models.BooksForRentDto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BooksForRentRepo extends CrudRepository<BooksForRentDto, UUID> {
    @Query("SELECT b FROM BooksForRentDto b JOIN b.bookRef br WHERE br.bookRefId = ?1")
    Optional<BooksForRentDto> findByBookRefId(UUID bookRefId);
    List<BooksForRentDto> findAll();

}
