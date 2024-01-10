package com.microservice.hotel.service.serviceImpl;

import com.microservice.hotel.service.entities.Hotel;
import com.microservice.hotel.service.exceptions.ResourceNotFoundException;
import com.microservice.hotel.service.repositories.HotelRepository;
import com.microservice.hotel.service.service.HotelService;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelRepository hotelRepository;
    @Override
    public Hotel createHotel(Hotel hotel) {
        String randomId = UUID.randomUUID().toString();
        hotel.setId(randomId);
        return this.hotelRepository.save(hotel);
    }

    @Override
    public Hotel getHotel(String id) {
        return this.hotelRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Hotel with" +
                " hotel id not exist in DB "+id));
    }

    @Override
    public List<Hotel> getAllHotels() {
        return this.hotelRepository.findAll();
    }
}
