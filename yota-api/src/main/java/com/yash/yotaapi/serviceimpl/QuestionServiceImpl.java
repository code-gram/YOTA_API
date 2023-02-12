package com.yash.yotaapi.serviceimpl;

import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.exception.QuestionException;
import com.yash.yotaapi.repository.QuestionRepository;
import com.yash.yotaapi.service.QuestionService;

/**
 * This is service layer class for Question Bank Management to write business logic
 * @author priya.m
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	/**
	 * This method is for save Question to DB through repository layer
	 */
	
	@Override
	public Question saveOrUpdate(Question question) {
		
		try {
			question.setQuestion(question.getQuestion().toUpperCase());
			return questionRepository.save(question);
		}catch(Exception ex) {
			throw new QueryException("Question : "+question.getQuestion().toUpperCase()+ "already exixts");
		}
		
	}

	@Override
	public Question findQuestionById(Long questionId) {
		Question question = questionRepository.findQuestionById(questionId);
		if (question==null) {
			throw new QuestionException("Question type :"+questionId+ "Does not exist");
		}
		return question;
	}

	/**
	 * This method is for find all Question from DB through repository layer
	 */
	
	@Override
	public Iterable<Question> findAllQuestion() {
		
		return questionRepository.findAll();
	}

	/**
	 * This method is for delete Question from DB through repository layer
	 */
	
	@Override
	public void deleteQuestionById(Long questionId) {
		
		Question question = questionRepository.findQuestionById(questionId);
		if (question==null) {
			throw new QuestionException("Question type :"+questionId+ "Does not exist");
		}
		
		questionRepository.delete(question);
	}

}
