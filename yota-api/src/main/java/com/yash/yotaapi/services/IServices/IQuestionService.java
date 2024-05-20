package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.QuestionsDto;
import com.yash.yotaapi.entity.Questions;
import java.util.List;

/**
 * Project Name - YOTA_NEW
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 24-04-2024
 */
public interface IQuestionService {

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
    QuestionsDto createQuestion(QuestionsDto questionsDto, Long techId, Long catId);

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
    QuestionsDto getQuestionById(Long questionId, Long techId, Long catId);

    /**
     * Get all questions data based on technology id and category id
     *
     * @param techId Long technology id
     * @param catId  Long category id
     * @return list of questions under category
     * @author yashr
     * @since 24-04-24
     */
    List<QuestionsDto> getAllQuestionsUnderCategory(Long techId, Long catId);

    /**
     * Get all questions data based on technology id
     *
     * @param techId Long technology id
     * @return list of questions under Technology
     * @author yashr
     * @since 24-04-24
     */
    List<QuestionsDto> getAllQuestionsUnderTechnology(Long techId);

    /**
     * Method to upload new question bank, please provide the technology id and category id to upload question bank
     *
     * @param excelDTOList    body object
     * @param techId       Long technology id
     * @param catId        Long category id
     *
     * @author amar sawant
     * @since 29-04-24
     */
    void saveExcelQuestions(List<Questions> excelDTOList, Long techId, Long catId);

   List<QuestionsDto> getQuestionByAssociateEmail(String email, Long testId);

   QuestionsDto updateQuestion(Long questionId, QuestionsDto questionsDto);

}
