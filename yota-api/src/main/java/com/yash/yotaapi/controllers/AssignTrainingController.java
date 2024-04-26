package com.yash.yotaapi.controllers;

import com.yash.yotaapi.entity.AssignTraining;
import com.yash.yotaapi.services.impls.AssignTrainingServiceImpl;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/assign")
public class AssignTrainingController {

    @Autowired
    AssignTrainingServiceImpl assignTrainingService;

    @PostMapping("/assign-training")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<AssignTraining> assignTraining(@RequestBody AssignTraining assignTraining) {
        return new ResponseEntity<>(assignTrainingService.assignTraining(assignTraining), HttpStatus.CREATED);
    }
}