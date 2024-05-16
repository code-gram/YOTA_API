package com.yash.yotaapi.controllers;


import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.dto.TrainingsDto;
import com.yash.yotaapi.dto.UserProfileDto;
import com.yash.yotaapi.dto.YotaUserDto;
import com.yash.yotaapi.services.IServices.ITestService;
import com.yash.yotaapi.services.IServices.IYOTAUserService;
import com.yash.yotaapi.services.impls.TestServiceImpl;
import com.yash.yotaapi.services.impls.TrainingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    private TestServiceImpl testService;

    @Autowired
    private ITestService iTestService;

    @Autowired
    private IYOTAUserService userService;

    @Autowired
    private TrainingServiceImpl trainingService;

    @GetMapping("/appeared")
    public ResponseEntity<Long> getApparedTestCountByEmail(@RequestParam String email) {
        Long appearedTestCount = testService.getAppearedTestCountByAssociateEmail(email);
        if (appearedTestCount == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appearedTestCount);
    }


    @GetMapping("/assignedTest")
    public ResponseEntity<List<TestsDto>> getTestByEmail(@RequestParam String email) {
        List<TestsDto> testList = testService.getTestsByAssociateEmail(email);
        if (testList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(testList);
    }

    @GetMapping("/testResult")
    public ResponseEntity<TestsDto> getTestResultByUserEmailAndTestId(
            @RequestParam String email,
            @RequestParam Long testId) {
        // try {
        TestsDto testsDto = testService.getTestResultByUserEmailAndTestId(email, testId);
        return ResponseEntity.ok(testsDto);
//        } catch (ApplicationException e) {
//            return ResponseEntity.status(404).body(null);
//        }
    }

    @GetMapping("/assigned")
    public ResponseEntity<List<TrainingsDto>> getTrainingByEmail(@RequestParam String email) {
        List<TrainingsDto> trainingList = trainingService.getTrainingByAssociateEmail(email);
        if (trainingList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trainingList);
    }

    @GetMapping("/get/user")
    public ResponseEntity<UserProfileDto> getUserByEmail(@RequestParam String email) {
        UserProfileDto user = userService.getUserByEmailAddress(email);
        return ResponseEntity.ok(user);
    }

}
