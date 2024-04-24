package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.QuestionsDto;
import com.yash.yotaapi.services.IServices.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 24-04-2024
 */
@RestController
@RequestMapping("/questions")
public class QuestionsController {

    @Autowired
    private IQuestionService questionService;

    @PostMapping("/create-new")
    public ResponseEntity<QuestionsDto> createQuestion(@RequestBody QuestionsDto questionsDto,
                                                       @RequestParam Long techId,
                                                       @RequestParam Long catId) {
        QuestionsDto question = this.questionService.createQuestion(questionsDto, techId, catId);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @GetMapping("/get/{questionId}")
    public ResponseEntity<QuestionsDto> getQuestionById(@PathVariable Long questionId,
                                                        @RequestParam Long techId,
                                                        @RequestParam Long catId) {
        QuestionsDto question = this
                .questionService
                .getQuestionById(questionId, techId, catId);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/get/all/cat/{catId}")
    public ResponseEntity<List<QuestionsDto>> getAllQuestionsUnderCategory(@RequestParam Long techId,
                                                                           @PathVariable Long catId) {
        List<QuestionsDto> questions = this
                .questionService
                .getAllQuestionsUnderCategory(techId, catId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/get/all/tech")
    public ResponseEntity<List<QuestionsDto>> getAllQuestionsUnderTechnology(@RequestParam Long techId) {
        List<QuestionsDto> questions = this
                .questionService
                .getAllQuestionsUnderTechnology(techId);
        return ResponseEntity.ok(questions);
    }
}
