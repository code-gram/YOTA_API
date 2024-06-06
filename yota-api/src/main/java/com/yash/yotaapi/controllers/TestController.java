package com.yash.yotaapi.controllers;

import java.util.List;
import java.util.Optional;


import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.validators.IsAssociate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.services.IServices.ITestService;
import com.yash.yotaapi.validators.IsTechnicalManager;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;

@RestController
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private ITestService testService;


    @PostMapping("/add-test")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<TestDto> addTest(@RequestBody TestDto testDto) {
        return new ResponseEntity<>(this.testService.addTest(testDto), HttpStatus.OK);
    }

    @GetMapping("/all-test")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<List<TestDto>> fetchAllTest() {
        return new ResponseEntity<List<TestDto>>(testService.fetchAllTest(), HttpStatus.OK);
    }

    @GetMapping("/testPaper")
    @IsAssociate
    public ResponseEntity<Optional<TestDto>> testPaper(@RequestParam("id") Long id) {
        Optional<TestDto> test = this.testService.findById(id);
        return new ResponseEntity<>(test, HttpStatus.OK);
    }

    @PostMapping("/assign-test-user/{testId}")
    public ResponseEntity<String> assignTestToUser(@PathVariable Long testId, @RequestBody List<Long> userIds) {
        try {
            testService.assignTestToUser(testId, userIds);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Test successfully assigned to user.");
        } catch (ApplicationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/add-question-in-test")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<String> addQuestionInTest(@RequestParam("testId") Long testId,
                                                    @RequestBody List<Long> questionIds) {
        String s = testService.addQuestionInTest(testId, questionIds);
        String responseBody = "{\"statusCode\": 201, \"data\": \"" + s + "\"}";
        return new ResponseEntity<>(responseBody,
                HttpStatus.CREATED);
    }

    @PutMapping("/total-question-count")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<String> updateQuestionCount(@RequestParam("totalQuestionCounts") Integer totalQuestionCount,
                                                      @RequestParam("testIds") Long testId) {
        return ResponseEntity.status(HttpStatus.OK).
                body(testService.updateTotalQuestionCount(totalQuestionCount, testId));
    }

}
