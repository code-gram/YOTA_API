package com.yash.yotaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.service.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Question Bank Controller", description = "Controller for Client Client Management")
@RequestMapping("/yota/api")
public class ClientController {

	@Autowired
	private ClientService clientQuestionBankService;

	@PostMapping("/client")
	public ResponseEntity<Client> createBatch(@Validated @RequestBody Client createClient) {

		clientQuestionBankService.createClient(createClient);
		return new ResponseEntity<Client>(HttpStatus.CREATED);
	}

	@GetMapping("/clients")
	public Iterable<Client> getAllQuestions() {
		return clientQuestionBankService.findAllQuestion();
	}
}
