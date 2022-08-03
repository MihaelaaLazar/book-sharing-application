package com.endava.controllers;


import com.endava.models.WaitingListDto;
import com.endava.services.WaitingListService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/waiting")
@Tag(name = "5. WaitingList", description = "WaitingList API")
@CrossOrigin(origins = "http://localhost:3000")
public class WaitingListController {

    @Autowired
    private WaitingListService waitingListService;

    @Operation(
            summary = "Add a user on the waiting list if the book is not available",
            description = "Adds a user on the waiting list if the book is not available"
    )
    @RequestMapping(
            value = "/{userId}/{bookId}",
            method = RequestMethod.POST)
    public ResponseEntity<?> addUserToWaitingList(@PathVariable UUID userId, @PathVariable UUID bookId) {
        return waitingListService.addUserToWaitingList(userId, bookId);
    }

    @Operation(
            summary = "Find all users on the waiting list",
            description = "Finds all users on the waiting list"
    )
    @RequestMapping(method = RequestMethod.GET)
    public List<WaitingListDto> getAllUsers() {
        return waitingListService.getAllUsers();
    }

    @Operation(
            summary = "Get info by userId",
            description = "Get info by userId"
    )
    @RequestMapping(method = RequestMethod.GET, value = "/{userId}")
    public ResponseEntity<?> getByUserId(@PathVariable UUID userId) {
        return waitingListService.getByUserId(userId);
    }
}
