package com.challenge.idincrement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.service.CurrentIdService;

@RestController
@RequestMapping(Paths.PATH_VERSION + Paths.PATH_CURRENT)
public class CurrentIdController {

	private CurrentIdService currentIdService;

	@Autowired
	public CurrentIdController(CurrentIdService currentIdService) {
		this.currentIdService = currentIdService;
	}

	@PostMapping
	public ResponseEntity<Integer> getCurrentId(@RequestBody ApiKey apiKey) {
		return new ResponseEntity<>(currentIdService.getCurrentId(apiKey), HttpStatus.ACCEPTED);
	}
}
