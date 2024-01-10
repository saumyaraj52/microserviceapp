package com.microservice.rating.service.service;

import com.microservice.rating.service.entities.Rating;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RatingService {
    Rating addRating(Rating rating);
    List<Rating> getAllRating();
    List<Rating> getAllUserRating(String userId);
    List<Rating> getAllHotelRating(String hotelId);
}
