package com.challenge.idincrement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.service.NextIdService;

@RestController
@RequestMapping(Paths.PATH_VERSION + Paths.PATH_NEXT)
public class NextIdController {

	private NextIdService nextIdService;

	@Autowired
	public NextIdController(NextIdService nextIdService) {
		this.nextIdService = nextIdService;
	}

	@PostMapping
	public ResponseEntity<Integer> getNextId(@RequestBody ApiKey apiKey) {
		return new ResponseEntity<>(nextIdService.getNextId(apiKey), HttpStatus.ACCEPTED);
	}
}
