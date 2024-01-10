package com.microservice.hotel.service.repositories;

import com.microservice.hotel.service.entities.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
    //Hotel findById(String id);

}
