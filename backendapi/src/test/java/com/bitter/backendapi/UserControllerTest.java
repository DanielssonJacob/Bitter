package com.bitter.backendapi;


import com.bitter.backendapi.UserFolder.User;
import com.bitter.backendapi.UserFolder.UserRepository;
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


import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MockMvc mvc;

    @Autowired
    ObjectMapper mapper;

    @Test
    void addUserObj() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/adduserobj")
                .content(mapper.writeValueAsString(new User(null, "NotJD", "password123","Not", "JD", LocalDate.now(),"mail@mail.com")))
                .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("NotJD")));
    }

    @Test
    void loginUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/adduserobj")
                        .content(mapper.writeValueAsString(new User(null, "JS", "password123","Not", "JD", LocalDate.now(),"mail@mail.com")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("JS")));

        mvc.perform(MockMvcRequestBuilders.post("/validate")
                        .content(mapper.writeValueAsString(new LoginForm("JS","password123")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("true")));

    }

    @Test
    void deleteUser() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/adduserobj")
                        .content(mapper.writeValueAsString(new User(null, "JS", "password123","Not", "JD", LocalDate.now(),"mail@mail.com")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(containsString("JS")));

        mvc.perform(MockMvcRequestBuilders.post("/deleteuser")
                        .content(mapper.writeValueAsString(new User(1L, "JS", "password123","Not", "JD", LocalDate.now(),"mail@mail.com")))
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful());

        mvc.perform(MockMvcRequestBuilders.get("/username/HS")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().string(not(containsString("JS"))));


    }

    @Test
    void addFriendsTest(){
        /*
        List<User> userList = Arrays.asList(new User(""));
        userRepository.saveAll();
         */

        if(userRepository.findByUsername("JD")!=null && userRepository.findByUsername("AS")!=null){
            User user = userRepository.findByUsername("JD");
            user.addFriend(userRepository.findByUsername("AS"));
            userRepository.save(user);
        }
        assertTrue(userRepository.findByUsername("JD").getFriends().get(0).getUsername().equals("AS"));


    }
}