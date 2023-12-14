package com.yash.yotaapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.domain.TrainingMaster;
import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.service.MasterService;
import com.yash.yotaapi.service.*;
import com.yash.yotaapi.service.TechnologyMasterService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;

@CrossOrigin("*")
@RestController
@RequestMapping("/masters")
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

	@GetMapping("/training-type")
	public ResponseEntity<List<TrainingMaster>> getTrainingTypes() {
		return new ResponseEntity<>(masterService.getTrainingTypeDetails(), HttpStatus.OK);
	}
}
