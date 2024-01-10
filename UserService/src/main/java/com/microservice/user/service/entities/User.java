package com.microservice.user.service.entities;

import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name="micro_user")
public class User {

    @Id
    @Column(name="ID")
     String id;
    @Column(name="NAME",length = 20)
    String name;
    @Column(name="EMAIL")
    String email;
    @Column(name="ABOUT")
    String about;

    //don't save in database
    @Transient
    List<Rating> ratingList= new ArrayList<>();


}
