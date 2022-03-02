package com.bitter.backendapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addUserObj() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/adduserobj")
                .content(mapper.writeValueAsString(new User(0, "NotJD", "password123","Not", "JD", LocalDate.now(),"mail@mail.com")))
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("NotJD")));
    }

    @Test
    void loginUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/validate")
                        .content(mapper.writeValueAsString(new LoginForm("JD","password123")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));

    }

    @Test
    void deleteUser() {
    }
}