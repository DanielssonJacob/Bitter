package com.bitter.backendapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BackendapiApplicationTests {

	@Autowired
	MockMvc mvc;

	@Autowired
	ObjectMapper mapper;

	@Test
	void contextLoads() {
	}

	@Test
	void createBeet() throws Exception{
		mvc.perform(MockMvcRequestBuilders.post("/beet")
				.content(mapper.writeValueAsString(new Beet(null, "hejhej", LocalDateTime.now(), "AS")))
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().is2xxSuccessful()).andExpect(MockMvcResultMatchers.content().string(containsString("hejhej")));

	}
}
