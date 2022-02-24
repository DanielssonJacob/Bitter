package com.bitter.backendapi;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BeetRepo {

    private List<Beet> beets;

    public void createBeet(Beet beet){
        beets.add(beet);
    }

    public void deleteBeet(long beetId){
        beets.removeIf(beet -> beet.getId() == beetId);
    }

    public List<Beet> getBeetByUser(String username) {
        List<Beet> userBeets = new ArrayList<>();

        for (Beet beet: beets) {
            if (beet.getCreatedByUsername().equals(username)){
                userBeets.add(beet);
            }
        }
        return userBeets;
    }

    public List<Beet> getBeets(){
        return beets;
    }

    public Beet getBeetById(long id){

        for (Beet beet: beets) {
            if (beet.getId() == id){
               return beet;
            }
        }
        return null;
    }

    public void editBeet(Beet newBeet){
        for (Beet beet : beets) {
            if(beet.getId() == newBeet.getId())
                beet.setId(newBeet.getId());
                beet.setMessage(newBeet.getMessage());
                beet.setCreatedAt(newBeet.getCreatedAt());
                beet.setCreatedByUsername(newBeet.getCreatedByUsername());
                break;
        }
    }

}
