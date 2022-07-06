package com.endava.repositories;

import com.endava.models.RentedBooksDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RentedBooksRepo extends CrudRepository<RentedBooksDto, UUID> {


}
