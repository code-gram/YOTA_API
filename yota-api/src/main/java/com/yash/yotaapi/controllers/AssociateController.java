package com.yash.yotaapi.controllers;


import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.dto.TrainingsDto;
import com.yash.yotaapi.services.impls.TestServiceImpl;
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

    @GetMapping("/appeared")
    public ResponseEntity<Long> getApparedTestCountByEmail(@RequestParam String email) {
        Long appearedTestCount = testService.getAppearedTestCountByAssociateEmail(email);
        if (appearedTestCount==null) {
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

}
