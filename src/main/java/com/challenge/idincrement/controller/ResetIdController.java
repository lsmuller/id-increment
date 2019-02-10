package com.challenge.idincrement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.idincrement.model.ApiKey;
import com.challenge.idincrement.service.ResetIdService;

@RestController
@RequestMapping(Paths.PATH_VERSION + Paths.PATH_RESET)

public class ResetIdController {

	private ResetIdService resetIdService;

	@Autowired
	public ResetIdController(ResetIdService resetIdService) {
		this.resetIdService = resetIdService;
	}

	@PutMapping
	public ResponseEntity<Integer> getNextId(@RequestBody ApiKey apiKey, @RequestParam Integer newId) {
		return new ResponseEntity<>(resetIdService.resetId(apiKey, newId), HttpStatus.ACCEPTED);
	}
}
