package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {

    @Query("select ques from Questions ques where ques.id=?1 and ques.category.id=?2")
    Optional<Questions> getQuestionById(Long questionId, Long catId);

    /**
     * Get all questions data based on technology id and category id
     *
     * @param techId Long technology id
     * @param catId  Long category id
     * @return set of questions under category
     * @author yashr
     * @since 24-04-24
     */
    @Query("select distinct ques from Questions ques where ques.category.technology.id=?1 and ques.category.id=?2")
    Optional<Set<Questions>> getAllQuestionsUnderCategory(Long techId, Long catId);

    /**
     * Get all questions data based on technology id
     *
     * @param techId Long technology id
     * @return set of questions under Technology
     * @author yashr
     * @since 24-04-24
     */
    @Query("select distinct ques from Questions ques where ques.category.technology.id=?1")
    Optional<Set<Questions>> getAllQuestionsUnderTechnology(Long techId);
}

