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
import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.service.ClientQuestionBankService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Question Bank Controller", description = "Controller for Client Question Bank")
@RequestMapping("/yota/api")
public class ClientQuestionBankController {

	@Autowired
	ClientQuestionBankService clientQuestionBankService;
	@Autowired
	private FieldErrorValidationUtillity mapValidationErrorService;

	@PostMapping("/createQuestion")
	public ResponseEntity<?> createClientQuestion(@Valid @RequestBody ClientQuestion question,
			BindingResult result) {
		ResponseEntity<?> errmap = mapValidationErrorService.validationError(result);
		if (errmap != null) {
			return errmap;
		}
		ClientQuestion savedQuestion = clientQuestionBankService.saveOrUpdate(question);
		return new ResponseEntity<ClientQuestion>(savedQuestion, HttpStatus.CREATED);
	}

	@GetMapping("/questions")
	public Iterable<ClientQuestion> getAllQuestions() {
		return clientQuestionBankService.findAllQuestion();
	}
}
