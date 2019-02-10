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
public class ResetIdService {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private IdEntityRepository idEntityRepository;

	private UserRequestValidator userRequestValidator;

	@Autowired
	public ResetIdService(IdEntityRepository idEntityRepository, UserRequestValidator userRequestValidator) {
		this.idEntityRepository = idEntityRepository;
		this.userRequestValidator = userRequestValidator;
	}

	public Integer resetId(ApiKey apiKey, Integer newId) {
		userRequestValidator.validateApiKey(apiKey.getApiKey());
		userRequestValidator.validateNewId(newId);
		IdEntity idEntity = idEntityRepository.findOneByApiKey(apiKey.getApiKey());
		logger.info("Entity found: {}", idEntity);
		userRequestValidator.validateIdEntity(idEntity);
		idEntity.setCurrentId(newId);
		logger.info("Integer {} will be reset as current for ApiKey {}", newId, apiKey);
		return idEntityRepository.save(idEntity).getCurrentId();
	}

}
