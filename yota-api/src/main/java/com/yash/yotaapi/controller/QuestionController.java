package com.yash.yotaapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.service.QuestionService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;


import io.swagger.v3.oas.annotations.tags.Tag;



/**
 * A controller basically controls the flow of the data.
 * It controls the data flow into model object and updates the view whenever data changes.
 * @author priya.m
 *
 */

@CrossOrigin("*")
@Tag(name="Question Controller", description="Controller for Question")

@RequestMapping("/yota/api/questions")
@RestController

public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private FieldErrorValidationUtillity mapValidationErrorService;
	
	/**
	 * This method will create new Question and save the question in DB.
	 * @param question
	 * @param result
	 * @return saved question
	 */
	

	@PostMapping("/")
	public ResponseEntity<?> createNewQuestion(@Valid @RequestBody Question question, BindingResult result){
		ResponseEntity<?> errmap = mapValidationErrorService.validationError(result);
		if(errmap!=null) { 
			return errmap;
		}

		Question savedQuestion = questionService.saveOrUpdate(question);
		return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED);
	}
	/**

	 * This method is used to get Question by using question Id.

	 * @param questionId
	 * @return questions
	 */
	@GetMapping("/{questionId}")
	public ResponseEntity<?> getQuestionById(@PathVariable Long questionId){
		Question question = questionService.findQuestionById(questionId);
		return new ResponseEntity<Question>(question, HttpStatus.OK);
	}
	
	/**
	 * This method is used to get all questions from DB.
	 * @return all questions
	 */
	@GetMapping("/all")
	public Iterable<Question> getAllQuestions(){
		return questionService.findAllQuestion();
	}
	
	/**

	 * This mentod is used to delete question by using question Id.

	 * @param questionId
	 * @return return message question is deleted
	 */
	@DeleteMapping("/{questionId}")
	public ResponseEntity<?> deleteQuestionById(@PathVariable Long questionId){
		questionService.deleteQuestionById(questionId);
		return new ResponseEntity<String>("question is deleted successfully!", HttpStatus.OK);
	}
	
	/**
	 * This mentod is used to update question by using question Id.
	 * @param questionId
	 * @return updated question
	 */

	@PutMapping("/")
	public ResponseEntity<?> updateQuestion(@Valid @RequestBody Question question,BindingResult result)
	{
		ResponseEntity<?> errorMap= mapValidationErrorService.validationError(result);
		if (errorMap!=null) {
			return errorMap;
		}

		return new ResponseEntity<Question>(questionService.updateQuestion(question), HttpStatus.OK);
	}

	
	@PostMapping("/questionUpload")
	public ResponseEntity<?> uploadExcelFile(@RequestParam("file") MultipartFile file) {

		if (ExcelHelper.checkExcelFormat(file)) {
			this.questionService.saveExcel(file);
		//	return ResponseEntity.ok(Map.of("message", "Excel File Uploaded Successfully"));
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File only.");

	}
	 
}
