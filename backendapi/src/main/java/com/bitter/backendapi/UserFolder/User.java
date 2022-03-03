package com.bitter.backendapi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usernametable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min=1, max=30)
    @Column(name="username")
    private String username;
    @Size(min=6, max=30)
    private String password;
    @Size(min=1, max=30)
    private String firstname;
    @Size(min=1, max=30)
    private String lastname;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="date_of_birth")
    private LocalDate dateOfBirth;
    @Email
    private String email;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> friends = new ArrayList<>();


    public User(Long id, String username, String password, String firstName, String lastName, LocalDate dateOfBirth, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstname = firstName;
        this.lastname = lastName;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
    }

    public void addFriend(User user){
        friends.add(user);
    }



    private String hashPassword(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }


}