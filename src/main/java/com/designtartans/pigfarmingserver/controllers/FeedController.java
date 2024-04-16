package com.designtartans.pigfarmingserver.controllers;

import com.designtartans.pigfarmingserver.dto.BodyResponse;
import com.designtartans.pigfarmingserver.dto.FeedDto;
import com.designtartans.pigfarmingserver.exceptions.FarmNotFoundException;
import com.designtartans.pigfarmingserver.services.FeedServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/feed")
@CrossOrigin(origins = "*")
public class FeedController {

    @Autowired
    private FeedServiceInterface feedService;

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<BodyResponse> createFeed(@RequestBody FeedDto feedDto) throws FarmNotFoundException {
        return new ResponseEntity<>(feedService.createFeed(feedDto), HttpStatus.CREATED);
    }

}
