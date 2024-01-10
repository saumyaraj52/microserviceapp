package com.microservice.user.service.service;

import com.microservice.user.service.entities.User;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User saveUser(User user);

    //updateUser(User user);
    User getUser(String id);

    //User getUserByName(String name);

    List<User> getAllUsers();

    User updateUser(String id);


    void deleteAllUser();
    void deleteUser(String id);


}
