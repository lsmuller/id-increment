package com.challenge.idincrement.validation;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.challenge.idincrement.exception.InvalidRequestException;
import com.challenge.idincrement.model.User;

@Component
public class UserRequestValidator {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	public void validateLoginData(User user) {
		logger.info("Validating user input {}", user);
		if (Objects.isNull(user) || isNullOrBlank(user.getEmail()) || isNullOrBlank(user.getPassword()) || isNullOrBlank(user.getTableName())){
			logger.error("User input is invalid");
			throw new InvalidRequestException("Fields e-mail, password and table name are mandatory");
		}
	}

	private boolean isNullOrBlank(String param) {
		return param == null || param.trim().length() == 0;
	}

}
