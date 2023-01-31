package com.yash.yotaapi.controller;

import javax.validation.Valid;

/**
 * Question Management Controller will facilitates CRUD functionalities
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.model.Question;
import com.yash.yotaapi.service.QuestionService;
import com.yash.yotaapi.serviceimpl.MapValidationErrorService;


@RestController
@RequestMapping("/api/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	@Autowired
	private MapValidationErrorService mapValidationErrorService;
	
	@PostMapping("")
	public ResponseEntity<?> createNewQuestion(@Valid @RequestBody Question question, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationError(result);
		if(errorMap!=null) return errorMap;
		Question savedQuestion = questionService.saveOrUpdate(question);
		return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED) ;
	}
	
	@GetMapping("/{questionType}")
	public ResponseEntity<?> getQuestionByType(@PathVariable String questionType){
		Question question = questionService.findByQuestionType(questionType);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<Question> getAllQuestions(){
		return questionService.findAllQuestion();
	}
	
	@DeleteMapping("/{questionType}")
	public ResponseEntity<?> deleteQuestion(@PathVariable String questionType){
		questionService.deleteQuestionByQuestionType(questionType);
		return new ResponseEntity<String>("question is deleted successfully!", HttpStatus.OK);
	}
}
