package com.microservice.hotel.service.service;

import com.microservice.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    public Hotel createHotel(Hotel hotel);

    public Hotel getHotel(String id);

    public List<Hotel> getAllHotels();


}
