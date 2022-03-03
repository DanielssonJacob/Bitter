package com.bitter.backendapi.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MessageController {

    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/message")
    public List<Message> messages(){
        return messageRepository.findAll();
    }

    @GetMapping("/message/{username}")
    public List<Message> messagesForUser (@PathVariable String username){
        return messageRepository.findBySendToUsername(username);
    }

    @PostMapping("/message")
    public Message sendMessage (@RequestBody Message message){
        return messageRepository.save(message);
    }




}
