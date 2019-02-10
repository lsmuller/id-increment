package com.challenge.idincrement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.challenge.idincrement.entity.IdEntity;
import com.challenge.idincrement.exception.InvalidRequestException;
import com.challenge.idincrement.exception.UnauthorizedException;
import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.repository.IdEntityRepository;
import com.challenge.idincrement.validation.UserRequestValidator;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NextIdControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private IdEntityRepository idRepository;

	@MockBean
	private UserRequestValidator userRequestValidator;

	@Test
	public void getCurrentIdShouldReturnApiKeyWhenUserExists() throws Exception {
		ApiKey apiKey = new ApiKey("dGVzdEB0ZXN0LmNvbToxMjM0OlVzZXI=");
		IdEntity idEntity = new IdEntity.Builder().withEmail("test@test.com").withPassword("1234").withTableName("User").withCurrentId(0).build();

		ObjectMapper mapper = new ObjectMapper();
		String apiKeyJson = mapper.writeValueAsString(apiKey);

		Mockito.when(idRepository.findOneByApiKey("dGVzdEB0ZXN0LmNvbToxMjM0OlVzZXI=")).thenReturn(idEntity);
		Mockito.when(idRepository.save(idEntity)).thenReturn(idEntity);

		mvc.perform(post("/next")
							.content(apiKeyJson)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isAccepted())
				.andExpect(content().string("1"));
	}

	@Test
	public void getCurrentIdShouldReturnUnauthorizedWhenApiKeyNotValid() throws Exception {
		User user = new User("test_2@test.com", "9876", "Material");
		IdEntity idEntity = new IdEntity.Builder().withApiKey("").build();

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);

		Mockito.when(idRepository.findOneByApiKey(ArgumentMatchers.any())).thenReturn(idEntity);
		Mockito.doThrow(new UnauthorizedException("")).when(userRequestValidator).validateIdEntity(idEntity);

		mvc.perform(post("/next").content(userJson)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void getCurrentIdShouldReturnBadRequestWhenRequestIsInvalid() throws Exception {
		User user = new User("", "", "");
		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(user);

		Mockito.doThrow(new InvalidRequestException("")).when(userRequestValidator).validateApiKey(ArgumentMatchers.any());
		mvc.perform(post("/next").content(userJson)
							.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}

}
