package com.challenge.idincrement.service;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.idincrement.exception.UnauthorizedException;
import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.repository.IdEntityRepository;

@Service
public class LoginService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private IdEntityRepository idEntityRepository;

	@Autowired
	public LoginService(IdEntityRepository idEntityRepository) {
		this.idEntityRepository = idEntityRepository;
	}

	public ApiKey login(User user) {
		logger.info("Trying to log user {}", user.getEmail());
		ApiKey apiKey = new ApiKey(idEntityRepository.findApiKeyByEmailPasswordAndTableName(user.getEmail(), user.getPassword(), user.getTableName()));
		validate(apiKey.getApiKey());
		return apiKey;
	}

	private void validate(String apiKey) {
		logger.info("Validating Api Key {}", apiKey);
		if (Objects.isNull(apiKey) || apiKey.isEmpty()) {
			throw new UnauthorizedException("User not found!");
		}
	}
}
