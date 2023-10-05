package com.yash.yotaapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yash.yotaapi.domain.CreateClient;
import com.yash.yotaapi.domain.CreateClientQuestion;
import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.service.CreateClientService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Question Bank Controller", description = "Controller for Client Question Bank")
@RequestMapping("/yota/api/client")
public class CreateClientController {

	@Autowired
	private CreateClientService clientQuestionBankService;
	
	@Autowired
	private FieldErrorValidationUtillity mapValidationErrorService;

	@PostMapping("/createclient")
	public ResponseEntity<CreateClient> createBatch(@Validated @RequestBody CreateClient createClient) {

		System.out.println("-------ClientQuestionBankService" + createClient.toString());

		clientQuestionBankService.createClient(createClient);
		// return new ResponseEntity<CreateClient>(batch, HttpStatus.CREATED);
		return new ResponseEntity<CreateClient>(HttpStatus.CREATED);
	}
	
	@GetMapping("/getclient")
	public Iterable<CreateClient> getAllQuestions() {
		return clientQuestionBankService.findAllQuestion();
	}
}
