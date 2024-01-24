package com.yash.yotaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.service.ClientService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

import io.swagger.v3.oas.annotations.tags.Tag;

import java.security.Principal;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Register", description = "Controller for Client Management")
@RequestMapping("/clients")
public class ClientController {

	@Autowired
	private ClientService clientQuestionBankService;

	@Autowired
	FieldErrorValidationUtillity fileErrorValidationUtillity;

	@PostMapping("/")
	public ResponseEntity<Client> createBatch(@Validated @RequestBody Client createClient, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		clientQuestionBankService.createClient(createClient);
		return new ResponseEntity<Client>(HttpStatus.CREATED);
	}

	@GetMapping("/")
	public Iterable<Client> getAllQuestions(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return clientQuestionBankService.findAllQuestion();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeClient(@PathVariable(value = "id") long id, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		clientQuestionBankService.removeClient(id);
		return new ResponseEntity<String>("Client with ID :" + id + " deleted.", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(@Validated @RequestBody Client client, @PathVariable long id,
			BindingResult result, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		ResponseEntity<?> errorMap = fileErrorValidationUtillity.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<Client>(clientQuestionBankService.updateClientDetails(client, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientDetailsById(@PathVariable long id, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		Client details = clientQuestionBankService.getClient(id);
		return new ResponseEntity<Client>(details, HttpStatus.OK);
	}
}