package com.yash.yotaapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.domain.ClientQuestions;
import com.yash.yotaapi.service.ClientQuestionBankService;
import com.yash.yotaapi.util.ExcelHelper;
import java.security.Principal;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin("*")
@RestController
@Tag(name = "Client Question Bank Controller", description = "Controller for Client Question Bank")
@RequestMapping("/client-questions")
public class ClientQuestionBankController {

	@Autowired
	ClientQuestionBankService clientQuestionBankService;

	@PostMapping("/")
	public ResponseEntity<?> createClientQuestion(@Valid @RequestBody ClientQuestions question, BindingResult result,
			Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<ClientQuestion> list = new ArrayList<>();
		ClientQuestion cq = new ClientQuestion();
		for (int i = 0; i < question.getClientQuestions().size(); i++) {
			cq = new ClientQuestion();
			cq.setAnswer((question.getClientQuestions().get(i)).getAnswer());
			cq.setQuestion((question.getClientQuestions().get(i)).getQuestion());
			cq.setClientId((question.getClientQuestions().get(i)).getClientId());
			list.add(cq);
		}
		List<ClientQuestion> savedQuestion = clientQuestionBankService.saveall(list);
		return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
	}

	@GetMapping("/")
	public Iterable<ClientQuestion> getClientQuestions(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return clientQuestionBankService.findQuestion();
	}

	@PostMapping("/upload-client-questions")
	public ResponseEntity<?> uploadClientQuestionExcelFile(@RequestParam("file") MultipartFile file,
			Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		if (ExcelHelper.checkExcelFormat(file)) {
			clientQuestionBankService.saveAll(file);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File only.");
	}

	@GetMapping("/{clientId}")
	public ResponseEntity<?> findQuestionById(@PathVariable long clientId, Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		List<ClientQuestion> details = clientQuestionBankService.getClientQuestions(clientId);
		return ResponseEntity.ok().body(details);
	}
}