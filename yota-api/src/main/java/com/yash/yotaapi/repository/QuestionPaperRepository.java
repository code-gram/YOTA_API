package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.QuestionPaper;
/**
 * QuestionPaperRepository Will Perform All CRUD Operations On QuestionPaper.
 *In Case If Any Customization Is Required ON CRUD Operation,It Can Be Done Here.
 *
 */
@Repository
public interface QuestionPaperRepository extends JpaRepository<QuestionPaper,Long> {

}
