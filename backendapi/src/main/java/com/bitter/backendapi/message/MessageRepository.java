package com.bitter.backendapi.message;

import com.bitter.backendapi.BeetFolder.Beet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {


    List<Message> findBySendToUsername(String sendToUsername);


}