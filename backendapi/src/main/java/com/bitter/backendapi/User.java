package com.bitter.backendapi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class User {


    long id;
    @Size(min=1, max=30)
    private String username;
    @Size(min=6, max=30)
    private String password;
    @Size(min=1, max=30)
    private String firstName;
    @Size(min=1, max=30)
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;
    @Email
    private String email;


    public User(long id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.username = username;
        this.password = hashPassword(password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    private String hashPassword(String password){
         return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }

    public void setPassword(String password) {
        this.password = this.hashPassword(password);
    }


}
