package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.UserTestAnswerDto;
import com.yash.yotaapi.entity.UserTestAnswer;
import com.yash.yotaapi.services.IServices.UserTestAnswersService;
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
    public UserTestAnswersController(UserTestAnswersService userTestAnswersService) {
        this.userTestAnswersService = userTestAnswersService;
    }

    @PostMapping("/user-test-answers")
    public ResponseEntity<String> saveUserTestAnswer(@RequestBody UserTestAnswerDto userTestAnswerDto) {
        UserTestAnswer savedAnswer = userTestAnswersService.saveUserTestAnswers(userTestAnswerDto);
        return ResponseEntity.status(HttpStatus.OK).body("entry saved successfully");
    }
}



