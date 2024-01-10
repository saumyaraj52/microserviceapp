package com.microservice.user.service.entities;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rating {

    private String ratingId;
    private String userId;
    private String hotelId;

    private int rating;

    private String about;
    private Hotel hotel;
}
