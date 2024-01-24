package com.yash.yotaapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.domain.TrainingMaster;
import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.service.MasterService;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
import java.security.Principal;

@CrossOrigin("*")
@RestController
@RequestMapping("/masters")
public class MasterController {

	@Autowired
	MasterService masterService;

	@GetMapping("/unit")
	public ResponseEntity<List<UnitMaster>> getUnits(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return new ResponseEntity<>(masterService.getUnitDetails(), HttpStatus.OK);
	}

	@GetMapping("/competency")
	public ResponseEntity<List<CompentencyMaster>> getCompetencies(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return new ResponseEntity<>(masterService.getCompetencyDetails(), HttpStatus.OK);
	}

	@GetMapping("/training-type")
	public ResponseEntity<List<TrainingMaster>> getTrainingTypes(Principal principal) {
		// Access authenticated user's details (username)
		String username = principal.getName();
		// Add your logic here

		return new ResponseEntity<>(masterService.getTrainingTypeDetails(), HttpStatus.OK);
	}
}