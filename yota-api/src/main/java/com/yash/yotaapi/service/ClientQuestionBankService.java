package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.ClientQuestion;


public interface ClientQuestionBankService {

	public ClientQuestion saveOrUpdate(ClientQuestion question);
	
	public Iterable<ClientQuestion> findAllQuestion();
}
