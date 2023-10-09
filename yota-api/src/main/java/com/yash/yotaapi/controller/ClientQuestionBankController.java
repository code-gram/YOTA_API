package com.yash.yotaapi.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yash.yotaapi.domain.CreateClientQuestion;
import com.yash.yotaapi.service.ClientQuestionBankService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Question Bank Controller", description = "Controller for Client Question Bank")
@RequestMapping("/yota/api/clientQuestion")
public class ClientQuestionBankController {

	@Autowired
	ClientQuestionBankService clientQuestionBankService;
	@Autowired
	private FieldErrorValidationUtillity mapValidationErrorService;

	@PostMapping("/add")
	public ResponseEntity<?> createClientQuestion(@Valid @RequestBody CreateClientQuestion question,
			BindingResult result) {
		ResponseEntity<?> errmap = mapValidationErrorService.validationError(result);
		if (errmap != null) {
			return errmap;
		}
		CreateClientQuestion savedQuestion = clientQuestionBankService.saveOrUpdate(question);
		return new ResponseEntity<CreateClientQuestion>(savedQuestion, HttpStatus.CREATED);
	}

	@GetMapping("/getAllQuestion")
	public Iterable<CreateClientQuestion> getAllQuestions() {
		return clientQuestionBankService.findAllQuestion();
	}
}
