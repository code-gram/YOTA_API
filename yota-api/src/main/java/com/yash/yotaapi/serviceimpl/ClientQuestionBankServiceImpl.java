package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.CreateClientQuestion;
import com.yash.yotaapi.exception.QuestionException;
import com.yash.yotaapi.repository.CreateClientQuestionRepository;
import com.yash.yotaapi.service.ClientQuestionBankService;

@Service
public class ClientQuestionBankServiceImpl implements ClientQuestionBankService {

	@Autowired
	CreateClientQuestionRepository createClientQuestionRepository;

	@Override
	public CreateClientQuestion saveOrUpdate(CreateClientQuestion question) {
		try {
			return createClientQuestionRepository.save(question);

		} catch (Exception ex) {
			throw new QuestionException("Question : " + question.getClientQuestion() + " already exixts");
		}

	}

	@Override
	public Iterable<CreateClientQuestion> findAllQuestion() {
		/*
		 * // TODO Auto-generated method stub List<CreateClientQuestion> a
		 * =createClientQuestionRepository.findAll(); for(CreateClientQuestion item:a){
		 * System.out.println("client id-----"+item.getClientId()); //
		 * createClientQuestionRepository.findById(item.getClientId()); }
		 */

		return createClientQuestionRepository.findAll();
	}

}
