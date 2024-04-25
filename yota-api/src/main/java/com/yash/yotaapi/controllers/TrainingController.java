package com.yash.yotaapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.services.impls.TrainingServiceImpl;
import com.yash.yotaapi.validators.IsTechnicalManager;

@RestController
@RequestMapping("/training")
public class TrainingController {

	@Autowired
	private TrainingServiceImpl trainingService;
	
	@PostMapping("/addTraining")
    @IsTechnicalManager
    public ResponseEntity<Trainings> addTraining(@RequestBody Trainings training) {
		System.out.println("api called");
		System.out.println(training);
        return new ResponseEntity<Trainings>(trainingService.addTraining(training), HttpStatus.CREATED);
    }
	
	@GetMapping("/listTraining")
    public ResponseEntity<List<Trainings>> listTraining() {
        return new ResponseEntity<List<Trainings>>(trainingService.listTraining(), HttpStatus.OK);
    }
}
