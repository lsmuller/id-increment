package com.challenge.idincrement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.challenge.idincrement.entity.IdEntity;
import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.repository.IdEntityRepository;
import com.challenge.idincrement.validation.UserRequestValidator;

@Service
public class NextIdService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private IdEntityRepository idEntityRepository;

	private UserRequestValidator userRequestValidator;

	@Autowired
	public NextIdService(IdEntityRepository idEntityRepository, UserRequestValidator userRequestValidator) {
		this.idEntityRepository = idEntityRepository;
		this.userRequestValidator = userRequestValidator;
	}

	public Integer getNextId(ApiKey apiKey) {
		userRequestValidator.validateApiKey(apiKey.getApiKey());
		IdEntity idEntity = idEntityRepository.findOneByApiKey(apiKey.getApiKey());
		userRequestValidator.validateIdEntity(idEntity);
		Integer current = idEntity.getCurrentId();
		idEntity.setCurrentId(++current);
		logger.info("Integer {} will be returned as current for ApiKey {}", current, apiKey);
		return idEntityRepository.save(idEntity).getCurrentId();
	}

}
