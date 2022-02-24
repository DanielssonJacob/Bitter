package com.bitter.backendapi;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    AtomicLong userId = new AtomicLong(6);


    public boolean validate(User user, String password){
        return BCrypt.checkpw(password,user.getPassword());
    }

    public User createUser(String username, String password, String firstname, String lastname, String email){
        return new User(userId.getAndIncrement(),username,password,firstname,lastname, LocalDate.now(),email);
    }

    public User createUser(User user){
        user.setId(userId.getAndIncrement());
        return user;
    }
}
