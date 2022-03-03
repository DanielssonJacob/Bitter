package com.bitter.backendapi.BeetFolder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BeetController {

    @Autowired
    OldBeetRepo repo;
    @Autowired BeetService service;

    @Autowired
    BeetRepository beetRepository;

    @GetMapping("/beet")
    public List<Beet> beets(){ return beetRepository.findAll();}

    //
    @PostMapping("/beet")
    public Beet postBeet(@RequestBody Beet beet){
        return beetRepository.save(service.createBeet(beet));
    }

    @GetMapping("/beetid/{id}")
    public Beet getBeetById(@PathVariable long id){
        return beetRepository.findById(id).orElse(new Beet());
    }

    @GetMapping("/beet/{username}")
    public List<Beet> getBeetByUser(@PathVariable String username){
        return beetRepository.findByCreatedByUsername(username);
    }

    @DeleteMapping("/beet/{id}")
    public void deleteBeet(@PathVariable long id){
       beetRepository.deleteById(id);
    }

    @PutMapping("/beet/{id}")
    public Beet editBeet(@PathVariable long id, String message){
        return service.editBeet(id, message);
    }

    @PutMapping("/editbeet")
    public void editBeet(@RequestBody Beet beet){
        beetRepository.save(beet);
    }

}
