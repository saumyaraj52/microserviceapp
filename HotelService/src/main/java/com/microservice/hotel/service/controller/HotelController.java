package com.microservice.hotel.service.controller;

import com.microservice.hotel.service.entities.Hotel;
import com.microservice.hotel.service.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelController {
    @Autowired
    private HotelService hotelService;

    //create
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel)
    {
        Hotel hotelCreated=this.hotelService.createHotel(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(hotelCreated);
    }

    //get single
    @PreAuthorize("hasAuthority('SCOPE_internal')")
    @GetMapping("/{hotelId}")
    ResponseEntity<Hotel> getSingleHotel(@PathVariable String hotelId)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.getHotel(hotelId));
    }

    //get all
    @PreAuthorize("hasAuthority('SCOPE_internal') || hasAuthority('Admin')")
    @GetMapping
    ResponseEntity<List<Hotel>> getAllHotels()
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.hotelService.getAllHotels());
    }

}
