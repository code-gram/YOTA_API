package com.yash.yotaapi.controllers;


import com.yash.yotaapi.services.impls.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/associate")
public class AssociateController {

    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/app")
    public ResponseEntity<Long> getApparedTestCountByEmail(@RequestParam String email) {
        Long appearedTestCount = testService.getAppearedTestCountByAssociateEmail(email);
        if (appearedTestCount==null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(appearedTestCount);
    }

}
