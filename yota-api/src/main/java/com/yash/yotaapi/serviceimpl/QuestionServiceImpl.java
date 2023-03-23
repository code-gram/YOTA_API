package com.yash.yotaapi.serviceimpl;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.Question;
import com.yash.yotaapi.exception.QuestionException;
import com.yash.yotaapi.repository.QuestionRepository;
import com.yash.yotaapi.service.QuestionService;

/**
 * This is service layer class for Question Bank Management to write business
 * logic
 * 
 * @author priya.m
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
			/*
			 * if(question.getQuestionType().equals("mcq")) {
			 * 
			 * }
			 */
			question.setQuestion(question.getQuestion());
			return questionRepository.save(question);

		}catch(Exception ex) {
			throw new QuestionException("Question : "+question.getQuestion()+ " already exixts");
			

		}

	}

	/**
	 * This method is for find question by id  from DB through repository layer
	 */
	@Override
	public Question findQuestionById(Long questionId) {
		Question question = questionRepository.findQuestionById(questionId);

		if (question==null) {
			throw new QuestionException("Question Id : " + questionId +  " Does not exist");

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
			throw new QuestionException("Question Id : "+questionId+ " Does not exist");

		}

		questionRepository.delete(question);
	}
	
	/**
	 * This method is for update Question to DB through repository layer
	 */
	@Override
	@Transactional
	public Question updateQuestion(Question question) {
		Question existQuestion=questionRepository.findQuestionById(question.getId());
		if (existQuestion==null) {
			return questionRepository.save(question);
		} else {
			existQuestion.setId(question.getId());
			existQuestion.setQuestionType(question.getQuestionType());
			existQuestion.setQuestionLevel(question.getQuestionLevel());
			existQuestion.setA(question.getA());
			existQuestion.setB(question.getB());
			existQuestion.setC(question.getC());
			existQuestion.setD(question.getD());
			existQuestion.setQuestion(question.getQuestion());
			existQuestion.setCorrectAnswer(question.getCorrectAnswer());
			/*
			 * existQuestion.setCreated_At(question.getCreated_At());
			 * existQuestion.setUpdated_At(question.getUpdated_At());
			 */
			
			questionRepository.save(existQuestion);
		}
		return question;
	}


}