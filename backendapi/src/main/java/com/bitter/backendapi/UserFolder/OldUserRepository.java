package com.bitter.backendapi.UserFolder;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class OldUserRepository {
    private List<User> users;

    public OldUserRepository(){
        users = new ArrayList<>();
        users.add(new User(1L,"JD","password123" ,"Jacob", "Danielsson", LocalDate.now(), "jd@mail.com"));
        users.add(new User(2L,"FF","password123" ,"Felix", "Frost", LocalDate.now(), "ff@mail.com"));
        users.add(new User(3L,"MV","password123" ,"Melody", "Vikström", LocalDate.now(), "mv@mail.com"));
        users.add(new User(4L,"AS","password123" ,"Ante", "Sundin", LocalDate.now(), "as@mail.com"));
        users.add(new User(5L,"AE","password123" ,"Amanda", "Eddestål", LocalDate.now(), "ae@mail.com"));
    }

    public User getUserById(Long id) {
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                return user;
            }
        }
        return null;
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public User addUser(User user){
        users.add(user);
        return user;
    }

    public void deleteUserById(Long id){
        for (User user : users) {
            if (Objects.equals(user.getId(), id)) {
                users.remove(user);
                break;
            }
        }
    }

    public List<User> getAllUsers(){
        return users;
    }

}
