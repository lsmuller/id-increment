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
import com.challenge.idincrement.service.CreateUserService;

@RestController
@RequestMapping("/new")
public class CreateUserController {

	private CreateUserService createUserService;

	@Autowired
	public CreateUserController(CreateUserService createUserService) {
		this.createUserService = createUserService;
	}

	@PostMapping
	public ResponseEntity<ApiKey> createUser(@RequestBody User user) {
		return new ResponseEntity<>(createUserService.createUser(user), HttpStatus.ACCEPTED);
	}

}
