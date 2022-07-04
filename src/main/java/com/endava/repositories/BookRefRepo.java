package com.endava.repositories;

import com.endava.models.BooksRefDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRefRepo extends CrudRepository<BooksRefDto, UUID> {
}
