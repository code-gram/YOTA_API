package com.yash.yotaapi.controllers;

import com.yash.yotaapi.dto.QuestionlistDto;
import com.yash.yotaapi.dto.QuestionsDto;
import com.yash.yotaapi.entity.Questions;
import com.yash.yotaapi.services.IServices.IQuestionService;
import com.yash.yotaapi.util.ExcelHelper;
import com.yash.yotaapi.validators.IsTechnicalManager;
import com.yash.yotaapi.validators.IsTechnicalManagerOrTrainer;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

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
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<QuestionsDto> createQuestion(@RequestBody QuestionsDto questionsDto,
                                                       @RequestParam Long techId,
                                                       @RequestParam Long catId) {
        QuestionsDto question = this.questionService.createQuestion(questionsDto, techId, catId);
        return new ResponseEntity<>(question, HttpStatus.CREATED);
    }

    @GetMapping("/get/{questionId}")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<QuestionsDto> getQuestionById(@PathVariable Long questionId,
                                                        @RequestParam Long techId,
                                                        @RequestParam Long catId) {
        QuestionsDto question = this
                .questionService
                .getQuestionById(questionId, techId, catId);
        return ResponseEntity.ok(question);
    }

    @GetMapping("/get/all/cat")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<List<QuestionsDto>> getAllQuestionsUnderCategory(@RequestParam Long techId, @RequestParam Long catId) {

        List<QuestionsDto> questions = this
                .questionService
                .getAllQuestionsUnderCategory(techId, catId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/get/all/tech")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<List<QuestionsDto>> getAllQuestionsUnderTechnology(@RequestParam Long techId) {
        List<QuestionsDto> questions = this
                .questionService
                .getAllQuestionsUnderTechnology(techId);
        return ResponseEntity.ok(questions);
    }

    @GetMapping("/get/list/tech")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<List<QuestionlistDto>> getQuestionsListUnderTechnology(@RequestParam Long techId) {
        List<QuestionlistDto> questions = this
                .questionService
                .getQuestionsListUnderTechnology(techId);
        return ResponseEntity.ok(questions);
    }

    @PostMapping("/upload-excel-questions")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<String> uploadExcelFile(@Valid @RequestParam("file") MultipartFile file, @RequestParam Long techId, @RequestParam Long catId) {
        try {
            if (!ExcelHelper.checkExcelFormat(file)) {
                return ResponseEntity.badRequest().body("Please upload an Excel file only.");
            }

            try (InputStream inputStream = file.getInputStream()) {

                List<Questions> excelQuestionList = ExcelHelper.convertExcelToListOfQuestion(inputStream);

                questionService.saveExcelQuestions(excelQuestionList, techId, catId);
                return ResponseEntity.ok("Excel File Uploaded Successfully");
            } catch (IOException e) {
                // Handle IOException
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the file.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/download-excel")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<Resource> downloadExcelFile() {
        try {
            Path fileLocation = Paths.get("src/main/resources/QuestionPaper.xlsx");
            Resource resource = new UrlResource(fileLocation.toUri());

            if(resource.exists()) {
                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/questionset/{testId}")
    public ResponseEntity<List<QuestionsDto>> getQuestionSetByEmailAndTestId(@RequestParam String email, @PathVariable Long testId) {
        List<QuestionsDto> questionset = questionService.getQuestionByAssociateEmail(email, testId);
        if (questionset.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(questionset);
    }

    @PutMapping("/{questionId}")
    public ResponseEntity<QuestionsDto> updateQuestion(@PathVariable Long questionId, @RequestBody QuestionsDto questionsDto) {
        QuestionsDto updatedQuestion = questionService.updateQuestion(questionId, questionsDto);
        return ResponseEntity.ok(updatedQuestion);
    }



    @DeleteMapping("/{id}")
    @IsTechnicalManagerOrTrainer
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {
        String message = questionService.deleteQuestionById(id);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/count-details")
    @IsTechnicalManager
    public ResponseEntity<HashMap<String, Integer>> countQuestionDetails(@RequestParam Long techId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.countQuestionDetails(techId));
    }
}
