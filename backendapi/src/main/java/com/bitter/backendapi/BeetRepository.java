package com.bitter.backendapi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface BeetRepository extends JpaRepository<Beet, Long> {


    List<Beet> findByCreatedByUsername(String username);


}