package com.challenge.idincrement.validation;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.challenge.idincrement.entity.IdEntity;
import com.challenge.idincrement.exception.InvalidRequestException;
import com.challenge.idincrement.exception.UnauthorizedException;
import com.challenge.idincrement.model.User;

@Component
public class UserRequestValidator {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public void validateLoginData(User user) throws InvalidRequestException{
		logger.info("Validating user input {}", user);
		if (Objects.isNull(user) || isNullOrBlank(user.getEmail()) || isNullOrBlank(user.getPassword()) || isNullOrBlank(user.getTableName())){
			logger.error("User input is invalid");
			throw new InvalidRequestException("Fields e-mail, password and table name are mandatory");
		}
	}

	public void validateApiKey(String apiKey) throws UnauthorizedException{
		logger.info("Validating Api Key: {}", apiKey);
		if (Objects.isNull(apiKey) || apiKey.isEmpty()) {
			throw new UnauthorizedException("User not found for apiKey " + apiKey + "!");
		}
	}

	public void validateIdEntity(IdEntity idEntity) throws UnauthorizedException{
		logger.info("Validating database record: {}", idEntity);
		if (Objects.isNull(idEntity) || isNullOrBlank(idEntity.getApiKey())) {
			throw new UnauthorizedException("User not found!");
		}
	}

	public void validateNewId(Integer newId) throws UnauthorizedException{
		logger.info("Validating provided value for new id: ", newId);
		if (Objects.isNull(newId)) {
			throw new InvalidRequestException("Field newId must be informed!");
		}
	}

	private boolean isNullOrBlank(String param) {
		return param == null || param.trim().length() == 0;
	}

}
