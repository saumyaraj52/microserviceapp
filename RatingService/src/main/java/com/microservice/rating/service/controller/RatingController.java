package com.microservice.rating.service.controller;

import com.microservice.rating.service.entities.Rating;
import com.microservice.rating.service.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ratings")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Rating> createRating(@RequestBody Rating rating)
    {
        Rating ratingCreated=this.ratingService.addRating(rating);
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingCreated);
    }

    @GetMapping
    ResponseEntity<List<Rating>> getAllRating()
    {
        List<Rating> ratingList=this.ratingService.getAllRating();
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingList);
    }

    @GetMapping("/hotels/{hotelId}")
    ResponseEntity<List<Rating>> getAllHotelRating(@PathVariable String hotelId)
    {
        List<Rating> ratingList=this.ratingService.getAllHotelRating(hotelId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingList);
    }

    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping("/users/{userId}")
    ResponseEntity<List<Rating>> getAllUserRating(@PathVariable String userId)
    {
        List<Rating> ratingList=this.ratingService.getAllUserRating(userId);
        return ResponseEntity.status(HttpStatus.FOUND).body(ratingList);
    }
}
