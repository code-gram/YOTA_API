package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.CreateClientQuestion;


public interface ClientQuestionBankService {

	public CreateClientQuestion saveOrUpdate(CreateClientQuestion question);
	
	public Iterable<CreateClientQuestion> findAllQuestion();
}
