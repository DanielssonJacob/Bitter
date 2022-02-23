package com.bitter.backendapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users;

    public UserRepository(){
        users = new ArrayList<>();
    }

    public User getUserById(Long id) {
        for (User user : users) {
            if (user.getId()==(id)) {
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
            if (user.getId()==id) {
                users.remove(user);
                break;
            }
        }
    }

    public List<User> getAllUsers(){
        return users;
    }

}
