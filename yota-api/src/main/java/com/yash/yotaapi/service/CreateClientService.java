package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.CreateClient;
import com.yash.yotaapi.domain.CreateClientQuestion;
import com.yash.yotaapi.domain.Question;

public interface CreateClientService {

	public CreateClient createClient(CreateClient createClient);
	
	public Iterable<CreateClient> findAllQuestion();
	
}
