package com.endava.repositories;


import com.endava.models.BooksForRentDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BooksForRentRepo extends CrudRepository<BooksForRentDto, UUID> {


}
