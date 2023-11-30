package com.yash.yotaapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.service.ClientService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.tags.Tag;

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
	public ResponseEntity<Client> createBatch(@Validated @RequestBody Client createClient) {

		clientQuestionBankService.createClient(createClient);
		return new ResponseEntity<Client>(HttpStatus.CREATED);
	}

	@GetMapping("/")
	public Iterable<Client> getAllQuestions() {
		return clientQuestionBankService.findAllQuestion();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeClient(@PathVariable(value = "id") long id) {
		clientQuestionBankService.removeClient(id);
		return new ResponseEntity<String>("Client with ID :" + id + " deleted.", HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateClient(@Validated @RequestBody Client client, @PathVariable long id,
			BindingResult result) {

		ResponseEntity<?> errorMap = fileErrorValidationUtillity.validationError(result);
		if (errorMap != null) {
			return errorMap;
		}
		return new ResponseEntity<Client>(clientQuestionBankService.updateClientDetails(client, id), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Client> getClientDetailsById(@PathVariable long id) {

		Client details = clientQuestionBankService.getClient(id);
		return new ResponseEntity<Client>(details, HttpStatus.OK);

	}
}
