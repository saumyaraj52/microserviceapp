package com.microservice.user.service.serviceImpl;

import com.microservice.user.service.external.services.hotelService;
import com.microservice.user.service.entities.Hotel;
import com.microservice.user.service.entities.Rating;
import com.microservice.user.service.entities.User;
import com.microservice.user.service.exceptions.ResourceNotFoundException;
import com.microservice.user.service.repositories.UserRepository;
import com.microservice.user.service.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.naming.Name;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private hotelService hotelService;

    private Logger logger= LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public User saveUser(User user) {
        //generate unique user id
        String randomUserId=UUID.randomUUID().toString();
        user.setId(randomUserId);
        return this.userRepository.save(user);
    }

    @Override
    public User getUser(String id) {
        User user= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with given id not " +
                "found in the DB "+id));
        //ArrayList<Rating> ratingList=restTemplate.getForObject("http://localhost:8082/ratings/users/"+user.getId(), ArrayList.class);
        //logger.info("{}",ratingList);

        Rating[] ratingList=restTemplate.getForObject("http://RATING-SERVICE/ratings/users/"+user.getId(), Rating[].class);
        logger.info("{}",ratingList);
        //Rating[] ratingsOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getUserId(), Rating[].class);
        //logger.info("{} ", ratingsOfUser);
        List<Rating> ratings = Arrays.stream(ratingList).toList();

        List<Rating> collectList=ratings.stream().map(rating -> {
            //api call to hotel microservice
            //ResponseEntity<Hotel> responseEntity=restTemplate.getForEntity("http://HOTEL-SERVICE/hotels/"+rating.getHotelId(), Hotel.class);
            //store the hotel info in rating
            //Hotel hotel=responseEntity.getBody();
            Hotel hotel= hotelService.getHotel(rating.getHotelId());
            rating.setHotel(hotel);
            //logger.info("response entity code {}",responseEntity.getStatusCode());
            return rating;
        }).collect(Collectors.toList());
        user.setRatingList(collectList);
        return user;
    }

    /*@Override
    public User getUserByName(String name) {
        User user = this.userRepository.findByName().orElseThrow(()->new ResourceNotFoundException("User with given id not " +
                "found in the DB "+id));
        return null;
    }*/

    @Override
    public List<User> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    @Override
    public User updateUser(String id) {
        User user= this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with given id not " +
                "found in the DB "+id));
        return user;
    }

    @Override
    public void deleteAllUser() {
        this.userRepository.deleteAll();
    }

    @Override
    public void deleteUser(String id) {
        User user = this.userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User with given id not " +
                "found in the DB "+id));
        this.userRepository.delete(user);
    }
}
