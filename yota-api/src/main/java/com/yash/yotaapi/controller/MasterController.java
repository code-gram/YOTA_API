package com.yash.yotaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TrainingTypeMaster;
import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.service.MasterService;

@CrossOrigin("*")
@RestController
@RequestMapping("/yota/api")
public class MasterController {
	
	@Autowired
	MasterService masterService;

	@GetMapping("/unit")
	public ResponseEntity<List<UnitMaster>> getUnits() {
		return new ResponseEntity<>(masterService.getUnitDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/competency")
	public ResponseEntity<List<CompentencyMaster>> getCompetencies() {
		return new ResponseEntity<>(masterService.getCompetencyDetails(), HttpStatus.OK);
	}
	
	@GetMapping("/trainingtype")
	public ResponseEntity<List<TrainingTypeMaster>> getTrainingTypes() {
		return new ResponseEntity<>(masterService.getTrainingTypeDetails(), HttpStatus.OK);
	}
	
}
