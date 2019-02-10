package com.challenge.idincrement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.model.User;
import com.challenge.idincrement.service.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@PostMapping
	public ResponseEntity<ApiKey> login(@RequestBody User user) {
		return new ResponseEntity<>(loginService.login(user), HttpStatus.ACCEPTED);
	}

}
