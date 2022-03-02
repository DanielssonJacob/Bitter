package com.bitter.backendapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BeetService {
    @Autowired
    OldBeetRepo repo;

    @Autowired
    BeetRepository beetRepository;

    public Beet createBeet(Beet beet) {
        beet.setCreatedAt(LocalDateTime.now());
        return beet;
    }

    public Beet editBeet(long id, String message){
        Beet beet = beetRepository.findById(id).orElseThrow();
        beet.setMessage(message);
        return beetRepository.save(beet);
    }


}
