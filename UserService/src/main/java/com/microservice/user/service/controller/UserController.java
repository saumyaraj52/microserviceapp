package com.microservice.user.service.controller;

import com.microservice.user.service.entities.User;
import com.microservice.user.service.service.UserService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        User userCreated = this.userService.saveUser(user);
        return new ResponseEntity<User>(userCreated, HttpStatus.CREATED);

    }
    int retryCount=0;
    @GetMapping("/{userId}")
    //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
    //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
    @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
    public ResponseEntity<User> getSingleUser(@PathVariable String userId)
    {
        logger.info("Retry count: {}");
        retryCount++;
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);

    }

    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());
        ex.printStackTrace();
        User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").id("141234").build();
        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers()
    {
        List<User> userList = this.userService.getAllUsers();
        return ResponseEntity.ok(userList);
    }

   /*@GetMapping("/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable User user)
    {
        User user = this.userService.getUser(userId);
        return ResponseEntity.ok(user);

    }*/

    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId)
    {
        this.userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteAllUser()
    {
        this.userService.deleteAllUser();
        return ResponseEntity.ok().build();
    }
}
