package com.endava.repositories;

import com.endava.models.WaitingListDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WaitingListRepo extends CrudRepository<WaitingListDto, UUID> {

}
