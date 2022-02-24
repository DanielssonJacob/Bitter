package com.bitter.backendapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BeetService {
    @Autowired BeetRepo repo;

    private final AtomicLong id = new AtomicLong(1452);

    public void createBeet(String username, String message) {
        Beet newBeet = new Beet(id.getAndIncrement(),message,LocalDateTime.now(),username);
        repo.createBeet(newBeet);
    }

    public void editBeet(long id, String message){
        Beet edited = new Beet(id, message, LocalDateTime.now(), repo.getBeetById(id).getCreatedByUsername());
        repo.editBeet(edited);
    }

}
