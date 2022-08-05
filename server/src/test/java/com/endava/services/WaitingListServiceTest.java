package com.endava.services;

import com.endava.models.WaitingListDto;
import com.endava.repositories.RentedBooksRepo;
import com.endava.repositories.WaitingListRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WaitingListServiceTest {
    @Mock
    private WaitingListRepo waitingListRepo;

    @Mock
    private RentedBooksRepo rentedBooksRepo;

    @InjectMocks
    private WaitingListService waitingListService;

    @Test
     void shouldReturnAllUsers() {
        when(waitingListRepo.findAll()).thenReturn(List.of(new WaitingListDto()));

        List<WaitingListDto> waitingList = waitingListService.getAllUsers();

        assertThat(waitingList).isNotNull();

    }

}
