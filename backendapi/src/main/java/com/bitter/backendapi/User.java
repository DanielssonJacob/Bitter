package com.bitter.backendapi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    long id;
    @Size(min=1, max=30)
    private String username;
    @Size(min=1, max=30)
    private String firstName;
    @Size(min=1, max=30)
    private String lastName;
    @Size(min=6, max=30)
    private String password;
    private LocalDate dateOfBirth;
    @Email
    private String email;

}
