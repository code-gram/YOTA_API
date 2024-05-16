package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.TrainingsDto;
import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.services.impls.TrainingServiceImpl;
import com.yash.yotaapi.validators.IsTechnicalManager;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {


    @Autowired
    private TrainingServiceImpl trainingService;

    @PostMapping("/addTraining")
    @IsTechnicalManager
    public ResponseEntity<Trainings> addTraining(@RequestBody Trainings training) {
        return new ResponseEntity<Trainings>(trainingService.addTraining(training), HttpStatus.CREATED);
    }

    @GetMapping("/listTraining")
    public ResponseEntity<List<Trainings>> listTraining() {
        return new ResponseEntity<List<Trainings>>(trainingService.listTraining(), HttpStatus.OK);
    }

    @PostMapping("/assign")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<Integer> assignTraining(@RequestParam("trainingId") Integer trainingId, @RequestBody List<String> emailIds) {
        return new ResponseEntity<>(trainingService.assignTraining(trainingId, emailIds), HttpStatus.CREATED);
    }

    @GetMapping("/registered-count")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<Integer> registeredCount(@RequestParam("trainingId") Integer trainingIds) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.registeredCount(trainingIds));
    }

    @GetMapping("/assigned-associated")
    public ResponseEntity<Trainings> assignedAssociated(@RequestParam("trainingId") Integer trainingIds) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.assignedAssociated(trainingIds));
    }

}
