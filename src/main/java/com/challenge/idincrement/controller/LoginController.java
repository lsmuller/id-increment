package com.challenge.idincrement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.idincrement.exception.UnauthorizedException;
import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.service.LoginService;

@RestController
@RequestMapping("/")
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping("login")
	public ResponseEntity<ApiKey> login(@RequestBody User user) {
		try {
			return new ResponseEntity<>(loginService.login(user), HttpStatus.ACCEPTED);
		} catch (UnauthorizedException e) {
			return new ResponseEntity<>(new ApiKey(), HttpStatus.UNAUTHORIZED);
		}
	}

}
