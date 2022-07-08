package com.endava.controllers;


import com.endava.models.UserDto;
import com.endava.services.WaitingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/waiting")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;


    @RequestMapping(
            value = "/{userId}/{bookId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> addUserToWaitingList(@PathVariable UUID userId, @PathVariable UUID bookId) {
        return waitingListService.addUserToWaitingList(userId, bookId);
    }
}
