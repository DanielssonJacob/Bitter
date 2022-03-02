package com.bitter.backendapi;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BeetRepository extends JpaRepository<Beet, Long> {


    List<Beet> findByCreatedByUsername(String username);


}