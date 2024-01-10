package com.microservice.rating.service.serviceImpl;

import com.microservice.rating.service.entities.Rating;
import com.microservice.rating.service.repositories.RatingRepository;
import com.microservice.rating.service.service.RatingService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating addRating(Rating rating) {
        String randomId = UUID.randomUUID().toString();
        rating.setRatingId(randomId);
        return this.ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getAllRating() {
        return this.ratingRepository.findAll();
    }

    @Override
    public List<Rating> getAllUserRating(String userId) {
        return this.ratingRepository.findAllByUserId(userId);
    }

    @Override
    public List<Rating> getAllHotelRating(String hotelId) {
        return this.ratingRepository.findAllByHotelId(hotelId);
    }
}
