package com.yash.yotaapi.serviceimpl;

import org.hibernate.QueryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.exception.QuestionException;
import com.yash.yotaapi.model.Question;
import com.yash.yotaapi.repository.QuestionRepository;
import com.yash.yotaapi.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

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
	public Question findByQuestionType(String questionType) {
		Question question = questionRepository.findByQuestionType(questionType.toUpperCase());
		if (question==null) {
			throw new QuestionException("Question type :"+questionType.toUpperCase()+ "Does not exist");
		}
		return question;
	}

	@Override
	public Iterable<Question> findAllQuestion() {
		
		return questionRepository.findAll();
	}

	@Override
	public void deleteQuestionByQuestionType(String questionType) {
		
		Question question = questionRepository.findByQuestionType(questionType.toUpperCase());
		if (question==null) {
			throw new QuestionException("Question type :"+questionType.toUpperCase()+ "Does not exist");
		}
		
		questionRepository.delete(question);
	}

}
