package com.yash.yotaapi.controllers;


import com.yash.yotaapi.exceptions.TrainingException;
import com.yash.yotaapi.services.IServices.IUserTrainingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-training")
public class UserTrainingTestController {

    @Autowired
    private IUserTrainingTestService userTrainingTestService;

    @PostMapping("/assign-test-to-training")
    public ResponseEntity<String> assignTestToTraining(@RequestParam Long testIds, @RequestParam Long trainingIds) {
        try {
            userTrainingTestService.assignTest(testIds, trainingIds);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Test successfully assigned to Training");
        } catch (TrainingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
