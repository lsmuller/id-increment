package com.challenge.idincrement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.repository.IdEntityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IdEntityRepository idRepository;

	@Test
	public void loginShouldReturnApiKeyWhenUserExists() throws Exception {
		User user = new User("test@test.com", "1234", "User");
		ApiKey apiKey = new ApiKey("dGVzdEB0ZXN0LmNvbToxMjM0");

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		String apiKeyJson = mapper.writeValueAsString(apiKey);

		Mockito.when(idRepository.findApiKeyByEmailPasswordTablename(user.getEmail(), user.getPassword(), user.getTableName())).thenReturn(apiKey.getApiKey());

		mvc.perform(post("/login").content(userJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isAccepted()).andExpect(content().string(apiKeyJson));
	}

	@Test
	public void loginShouldReturnUnauthorizedWhenUserDontExist() throws Exception {
		User user = new User("test_2@test.com", "9876", "Material");
		ApiKey apiKey = new ApiKey();

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);
		String apiKeyJson = mapper.writeValueAsString(apiKey);

		Mockito.when(idRepository.findApiKeyByEmailPasswordTablename(user.getEmail(), user.getPassword(), user.getTableName())).thenReturn(apiKey.getApiKey());

		mvc.perform(post("/login").content(userJson).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized()).andExpect(content().string(apiKeyJson));
	}

	@Test
	public void loginShouldReturnBadRequestWhenRequestIsInvalid() throws Exception {
		mvc.perform(post("/login").content("{test}").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
	}
}
