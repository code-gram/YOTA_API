package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.CategoryDto;
import com.yash.yotaapi.dto.QuestionlistDto;
import com.yash.yotaapi.dto.QuestionsDto;
import com.yash.yotaapi.entity.Category;
import com.yash.yotaapi.entity.Questions;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.QuestionDeletionException;
import com.yash.yotaapi.exceptions.QuestionNotFoundException;
import com.yash.yotaapi.repositories.CategoryRepository;
import com.yash.yotaapi.repositories.QuestionsRepository;
import com.yash.yotaapi.repositories.TechnologyRepository;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.repositories.UserTestAnswersRepository;
import com.yash.yotaapi.services.IServices.ICategoryService;
import com.yash.yotaapi.services.IServices.IQuestionService;
import com.yash.yotaapi.services.IServices.ITechnologyService;
import org.apache.commons.lang3.ObjectUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 24-04-2024
 */
@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionsRepository questionsRepository;

    @Autowired
    private ITechnologyService technologyService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    TechnologyRepository technologyRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    TestRepository testRepository;
    @Autowired
    UserTestAnswersRepository userTestAnswersRepository;


    /**
     * Method to create new question, please provide the technology id and category id to create question
     *
     * @param questionsDto DTO body object
     * @param techId       Long technology id
     * @param catId        Long category id
     * @return newly created question object
     * @author yashr
     * @since 24-04-24
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public QuestionsDto createQuestion(QuestionsDto questionsDto,
                                       Long techId,
                                       Long catId) {
        Questions questions = null;
        if (ObjectUtils.isNotEmpty(questionsDto)) {
            CategoryDto category = this
                    .categoryService
                    .findCategoryByTechnologyIdAndCategoryId(techId, catId);

            questionsDto.setCategory(category);

            questions = this
                    .mapper
                    .map(questionsDto, Questions.class);

            questions = this
                    .questionsRepository.save(questions);

            questionsDto = this
                    .mapper
                    .map(questions, QuestionsDto.class);

            questions = null;
            return questionsDto;
        } else
            throw new ApplicationException("Invalid Question details, please check and Try again...");
    }

    /**
     * Get question data based on technology id and category id
     *
     * @param questionId Long question id against which the data is required
     * @param techId     Long technology id
     * @param catId      Long category id
     * @return newly created question object
     * @author yashr
     * @since 24-04-24
     */
    @Override
    public QuestionsDto getQuestionById(Long questionId,
                                        Long techId,
                                        Long catId) {
        Questions questions = null;
        if (ObjectUtils.isNotEmpty(questionId)) {

            CategoryDto category = this
                    .categoryService
                    .findCategoryByTechnologyIdAndCategoryId(techId, catId);

            questions = this
                    .questionsRepository
                    .getQuestionById(questionId, category.getId())
                    .orElseThrow(() -> new ApplicationException("Question not found..."));

            QuestionsDto questionsDto = this
                    .mapper
                    .map(questions, QuestionsDto.class);

            questionsDto.setCategory(category);

            questions = null;
            return questionsDto;
        } else
            throw new ApplicationException("Invalid Question details, please check and Try again...");
    }

    /**
     * Get all questions data based on technology id and category id
     *
     * @param techId Long technology id
     * @param catId  Long category id
     * @return List of questions under category
     * @author yashr
     * @since 24-04-24
     */
    @Override
    public List<QuestionsDto> getAllQuestionsUnderCategory(Long techId, Long catId) {
        if (ObjectUtils.isNotEmpty(techId)
                && ObjectUtils.isNotEmpty(catId)) {
            Set<Questions> questionsSet = this
                    .questionsRepository
                    .getAllQuestionsUnderCategory(techId, catId)
                    .orElseThrow(() -> new ApplicationException("Question not found..."));

            if (!questionsSet.isEmpty()) {
                return questionsSet
                        .stream()
                        .map(ques -> this
                                .mapper
                                .map(ques, QuestionsDto.class))
                        .collect(Collectors.toList());
            }
        } else
            throw new ApplicationException("Provided details are invalid or empty, please check and try again...");
        return Collections.emptyList();
    }

    /**
     * Get all questions data based on technology id
     *
     * @param techId Long technology id
     * @return List of questions under Technology
     * @author yashr
     * @since 24-04-24
     */
    @Override
    public List<QuestionsDto> getAllQuestionsUnderTechnology(Long techId) {
        if (ObjectUtils.isNotEmpty(techId)) {
            Set<Questions> questionsSet = this
                    .questionsRepository
                    .getAllQuestionsUnderTechnology(techId)
                    .orElseThrow(() -> new ApplicationException("Question not found..."));

            return questionsSet
                    .stream()
                    .map(ques -> this
                            .mapper
                            .map(ques, QuestionsDto.class))
                    .collect(Collectors.toList());
        } else
            throw new ApplicationException("Provided details are invalid or empty, please check and try again...");
    }

    /**
     * Method to upload new question bank, please provide the technology id and category id to upload question bank
     *
     * @param excelQuestionList body object
     * @param techId            Long technology id
     * @param catId             Long category id
     * @author amar sawant
     * @since 29-04-24
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void saveExcelQuestions(List<Questions> excelQuestionList, Long techId, Long catId) {

        try {

            Optional<Technology> optionalTechnology = technologyRepository.findById(techId);
            if (!optionalTechnology.isPresent()) {
                throw new IllegalArgumentException("Technology with ID " + techId + " not found");
            }
            Technology technology = optionalTechnology.get();

            Optional<Category> optionalCategory = categoryRepository.findById(catId);
            if (!optionalCategory.isPresent()) {
                throw new IllegalArgumentException("Category with ID " + catId + " not found");
            }

            Category category = optionalCategory.get();
            category.setTechnology(technology);

            for (Questions question : excelQuestionList) {
                question.setCategory(category);
            }

            questionsRepository.saveAll(excelQuestionList);
            System.out.println("Excel data saved successfully");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while processing the file", e);
        }
    }

    @Override
    public List<QuestionsDto> getQuestionByAssociateEmail(String email, Long testid) {
//        List<Long> testIds = testRepository.getTestIdByEmailId(email);
//        if (testIds.isEmpty() || !testIds.contains(testid)) {
//            throw new ApplicationException("Check emailId : " + email+ "  And testId:-"+testid+ " which you provided has not assign any test");
//        }
        List<QuestionsDto> questionsDtos = new ArrayList<>();
            List<Long> questionIds = testRepository.getQuestionIdByTestId(testid);
            for (Long questionId : questionIds) {
                Questions question = questionsRepository.getQuestionsByAllId(questionId);
                if (question != null) {
                    QuestionsDto questionsDto=new QuestionsDto();
                    questionsDto.setId(question.getId());
                    questionsDto.setQuestionTitle(question.getQuestionTitle());
                    questionsDto.setOption_A(question.getOption_A());
                    questionsDto.setOption_B(question.getOption_B());
                    questionsDto.setOption_C(question.getOption_C());
                    questionsDto.setOption_D(question.getOption_D());
                    questionsDto.setCorrectAnswer(question.getCorrectAnswer());
                    questionsDtos.add(questionsDto);
                }
            }
        return questionsDtos;
    }

    @Override
    @Transactional
    public QuestionsDto updateQuestion(Long questionId, QuestionsDto questionsDto) {
        Questions question = questionsRepository.findById(questionId).orElseThrow(() -> new ApplicationException("Question not found with id: " + questionId));

        question.setQuestionTitle(questionsDto.getQuestionTitle());
        question.setCorrectAnswer(questionsDto.getCorrectAnswer());
        question.setOption_A(questionsDto.getOption_A());
        question.setOption_B(questionsDto.getOption_B());
        question.setOption_C(questionsDto.getOption_C());
        question.setOption_D(questionsDto.getOption_D());
        question.setQuestionLevel(questionsDto.getQuestionLevel());
        question.setUpdated_At(questionsDto.getUpdated_At());
        questionsRepository.save(question);
        Questions questions1 = questionsRepository.findById(questionId).orElseThrow(() -> new ApplicationException("Question not found with id: " + questionId));

        questionsDto = this
                .mapper
                .map(questions1, QuestionsDto.class);
        return questionsDto;
    }

    @Override
    public List<QuestionlistDto> getQuestionsListUnderTechnology(Long techId) {
        if (ObjectUtils.isNotEmpty(techId)) {
            Set<Questions> questionsSet = this
                    .questionsRepository
                    .getAllQuestionsUnderTechnology(techId)
                    .orElseThrow(() -> new ApplicationException("Question not found..."));

            return questionsSet
                    .stream()
                    .map(ques -> this
                            .mapper
                            .map(ques, QuestionlistDto.class))
                    .collect(Collectors.toList());
        } else
            throw new ApplicationException("Provided details are invalid or empty, please check and try again...");    }

    @Override
    public String deleteQuestionById(Long id) {
        if (!questionsRepository.existsById(id)) {
            throw new QuestionNotFoundException("Question not found with id " + id);
        }
        if (userTestAnswersRepository.existsByQuestionId(id)) {
            throw new QuestionDeletionException("Question with id " + id + " cannot be deleted because it is referenced in user test answers");
        }
        questionsRepository.deleteById(id);
        return "Question deleted successfully";
    }

    @Override
    public HashMap<String, Integer> countQuestionDetails(Long techId) {
        HashMap<String, Integer> integerHashMap = new HashMap<>();
        integerHashMap.put("questionCount", questionsRepository.questionCount(techId));
        integerHashMap.put("easyCount", questionsRepository.easyQuestionCount(techId));
        integerHashMap.put("mediumCount", questionsRepository.mediumQuestionCount(techId));
        integerHashMap.put("hardCount", questionsRepository.hardQuestionCount(techId));
        return integerHashMap;
    }

}
