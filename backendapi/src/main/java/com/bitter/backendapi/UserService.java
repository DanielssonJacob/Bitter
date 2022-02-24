package com.bitter.backendapi;


import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {


    public boolean validate(User user, String password){
        return BCrypt.checkpw(password,user.getPassword());
    }
}
