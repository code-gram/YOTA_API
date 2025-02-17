package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.TestEmployeeResult;
import com.yash.yotaapi.dto.TprReportDto;
import com.yash.yotaapi.dto.TrainingListDto;
import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.services.impls.TrainingServiceImpl;
import com.yash.yotaapi.util.ValidateRequestUtility;
import com.yash.yotaapi.util.ValidationUtility;
import com.yash.yotaapi.validators.IsTechnicalManager;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/training")
public class TrainingController {


    @Autowired
    private TrainingServiceImpl trainingService;
    @Autowired
    ValidateRequestUtility validateRequestUtility;

    @PostMapping("/addTraining")
    @IsTechnicalManager
    public ResponseEntity<?> addTraining(@RequestBody Trainings training) {
        Map<String, String> validationErrors = validateRequestUtility.validateRequest(training);

        if (!validationErrors.isEmpty()  ||!ValidationUtility.isEndDateGreater(training.getStartDate(),training.getEndDate())){
            if(!ValidationUtility.isAlphabetic(training.getTrainingName())){
                validationErrors.put("tName", "Training Name should not be empty");
            }
            if(training.getStartDate()!=null && training.getEndDate()!=null) {
                validationErrors.put("sDate", "Start Date should be before end date");
                validationErrors.put("eDate", "End Date should be after start date");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
        }
        return new ResponseEntity<Trainings>(trainingService.addTraining(training), HttpStatus.CREATED);
    }

    @GetMapping("/listTraining")
    public ResponseEntity<List<TrainingListDto>> listTraining() {
        return new ResponseEntity<List<TrainingListDto>>(trainingService.listTraining(), HttpStatus.OK);
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

    @PostMapping("/assign-test-to-training")
    public ResponseEntity<String> assignTestToTrainings(@RequestParam Long testIds, @RequestParam Long trainingIds) {
        try {
            trainingService.assignTestTraining(testIds, trainingIds);
            String responseBody = "{\"statusCode\": 201, \"data\": \"" + "Test successfully assigned to Training." + "\"}";
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(responseBody);
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
    
    @GetMapping("/getTprReport/{trainingId}")
    public ResponseEntity<List<TprReportDto>> getTprReport(@PathVariable("trainingId") Integer trainingIds) {
        return ResponseEntity.status(HttpStatus.OK).body(trainingService.getTprReport(trainingIds));
    }
    @GetMapping("/getEmpWiseTstDtls/{trainingId}/{empId}")
    public ResponseEntity<List<TestEmployeeResult>> getEmployeWiseTestRpt(@PathVariable("trainingId") Integer trainingIds,@PathVariable("empId") Integer empId) {
    	return ResponseEntity.status(HttpStatus.OK).body(trainingService.getEmployeeWiseTestDetails(trainingIds, empId));
    }


    @GetMapping("/get-all-assigned-training")
    public Set<Map<String, Object>> getAllAssignedTraining() {
        return trainingService.getAllAssignedTraining();
    }

}
