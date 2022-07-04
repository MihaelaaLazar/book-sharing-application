package com.endava.repositories;

import com.endava.models.BookDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepo extends CrudRepository<BookDto, UUID> {
}

