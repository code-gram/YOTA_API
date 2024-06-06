package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.TestResultDto;
import com.yash.yotaapi.dto.UserTestAnswerDto;
import com.yash.yotaapi.entity.TestResult;
import com.yash.yotaapi.entity.UserTestAnswer;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.ResourceNotFoundException;
import com.yash.yotaapi.services.IServices.ITestResultService;
import com.yash.yotaapi.services.IServices.UserTestAnswersService;
import com.yash.yotaapi.services.impls.TestResultServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserTestAnswersController {

    private final UserTestAnswersService userTestAnswersService;

    @Autowired
    private ITestResultService testResultService;

    @Autowired
    public UserTestAnswersController(UserTestAnswersService userTestAnswersService) {
        this.userTestAnswersService = userTestAnswersService;
    }

    @PostMapping("/user-test-answers")
    public ResponseEntity<String> saveUserTestAnswer(@RequestBody UserTestAnswerDto userTestAnswerDto) {
        try {
            UserTestAnswer savedAnswer = userTestAnswersService.saveUserTestAnswers(userTestAnswerDto);
            return ResponseEntity.status(HttpStatus.OK).body("Entry saved successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @PostMapping("/add-result")
    public ResponseEntity<String> createTestResult(@RequestBody TestResultDto testResultDto) {
        try{
            TestResult testResult=testResultService.saveTestResult(testResultDto);
            if(testResult==null){
                throw new ApplicationException("Test Not Submitted");
            }
            return ResponseEntity.status(HttpStatus.CREATED).body("Test Submitted successfully");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}



