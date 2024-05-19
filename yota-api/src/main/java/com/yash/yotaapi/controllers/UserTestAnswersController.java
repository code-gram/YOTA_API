package com.yash.yotaapi.controllers;

import com.yash.yotaapi.entity.UserTestAnswer;
import com.yash.yotaapi.services.IServices.UserTestAnswersService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserTestAnswer saveUserTestAnswers(@RequestBody UserTestAnswer userTestAnswer) {
        return userTestAnswersService.saveUserTestAnswers(userTestAnswer);
    }
}

//{
//        "testId": 123,
//        "userId": 456,
//        "questionId": 789,
//        "selectedOption": "option_A"
//        }

