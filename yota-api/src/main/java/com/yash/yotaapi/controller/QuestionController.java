package com.yash.yotaapi.controller;
 
import java.security.Principal;
import java.util.List;
 
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
 
import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.service.QuestionService;
import com.yash.yotaapi.service.TechnologyMasterService;
import com.yash.yotaapi.util.ExcelHelper;
import com.yash.yotaapi.util.FieldErrorValidationUtillity;
 
import io.swagger.v3.oas.annotations.tags.Tag;
 
/**
* A controller basically controls the flow of the data. It controls the data
* flow into model object and updates the view whenever data changes.
*
* @author priya.m
*/
@CrossOrigin("*")
@Tag(name = "Question Controller", description = "Controller for Question")
@RequestMapping("/questions")
@RestController
public class QuestionController {
 
    @Autowired
    private QuestionService questionService;
 
    @Autowired
    private FieldErrorValidationUtillity mapValidationErrorService;
 
    @Autowired
    private TechnologyMasterService technologyMasterService;
 
    /**
     * This method will create new Question and save the question in DB.
     *
     * @param question
     * @param result
     * @return saved question
     */
    @GetMapping("/")
    public List<TechnologyMaster> loadForm() {
        List<TechnologyMaster> lst = technologyMasterService.getAllTechs();
        return lst;
    }
 
    @PostMapping("/")
    public ResponseEntity<?> createNewQuestion(@Valid @RequestBody Question question, BindingResult result,
            Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        String question1 = question.getQuestion().replaceAll("<[^>]+>", "");
        question.setQuestion(question1);
 
        ResponseEntity<?> errmap = mapValidationErrorService.validationError(result);
        if (errmap != null) {
            return errmap;
        }
        Question savedQuestion = questionService.saveOrUpdate(question);
        return new ResponseEntity<Question>(savedQuestion, HttpStatus.CREATED);
    }
 
    /**
     * This method is used to get Question by using question Id.
     *
     * @param questionId
     * @return questions
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable Long id) {
        Question question = questionService.findQuestionById(id);
        return new ResponseEntity<Question>(question, HttpStatus.OK);
    }
 
    /**
     * This method is used to get all questions from DB.
     *
     * @return all questions
     */
    @GetMapping("/all")
    public Iterable<Question> getAllQuestions() {
        return questionService.findAllQuestion();
    }
 
    /**
     * This method is used to delete question by using question Id.
     *
     * @param questionId
     * @return return message question is deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return new ResponseEntity<String>("question is deleted successfully!", HttpStatus.OK);
    }
 
    /**
     * This method is used to update question by using question Id.
     *
     * @param questionId
     * @return updated question
     */
    @PutMapping("/")
    public ResponseEntity<?> updateQuestion(@Valid @RequestBody Question question, BindingResult result,
            Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        ResponseEntity<?> errorMap = mapValidationErrorService.validationError(result);
        if (errorMap != null) {
            return errorMap;
        }
        return new ResponseEntity<Question>(questionService.updateQuestion(question), HttpStatus.OK);
    }
 
	@PostMapping("/upload-questions")
	public ResponseEntity<?> uploadExcelFile(@RequestPart("file") MultipartFile file,
			@RequestParam("technologyId") String technologyId, @RequestParam("test") String test, Principal principal) {
 
		String username = principal.getName();
 
		if (test.equals("") || technologyId.equals("")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please select Both Technology and Test Name.");
		} else {
			if (ExcelHelper.checkExcelFormat(file)) {
				System.out.println("check3");
				questionService.saveExcel(file, technologyId, test);
				return new ResponseEntity<String>("Excel File Uploaded Successfully", HttpStatus.OK);
			}
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please upload Excel File only.");
		}
	}
	
    @PostMapping("/question-code")
    public ResponseEntity<Question> saveCode(@RequestBody String question, Principal principal) {
        // Access authenticated user's details (username)
        String username = principal.getName();
        // Add your logic here
 
        Question qs = new Question();
        qs.setQuestion(question);
        qs = questionService.saveOrUpdate(qs);
        return ResponseEntity.ok(qs);
    }
}