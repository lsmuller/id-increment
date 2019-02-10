package com.challenge.idincrement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.idincrement.entity.IdEntity;
import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.repository.IdEntityRepository;
import com.challenge.idincrement.validation.UserRequestValidator;

@Service
public class CreateUserService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private IdEntityRepository idEntityRepository;

	private UserRequestValidator userRequestValidator;

	@Autowired
	public CreateUserService(IdEntityRepository idEntityRepository, UserRequestValidator userRequestValidator) {
		this.idEntityRepository = idEntityRepository;
		this.userRequestValidator = userRequestValidator;
	}

	public ApiKey createUser(User user) {
		userRequestValidator.validateLoginData(user);
		logger.info("Creating user {}", user.getEmail());
		IdEntity idEntity = new IdEntity.Builder().withEmail(user.getEmail()).withPassword(user.getPassword()).withTableName(user.getTableName()).withCurrentId(0).build();
		idEntity = idEntityRepository.save(idEntity);
		userRequestValidator.validateApiKey(idEntity.getApiKey());
		return new ApiKey(idEntity.getApiKey());
	}

}
