package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.domain.Question;

public interface ClientService {

	public Client createClient(Client createClient);
	
	public Iterable<Client> findAllQuestion();
	
}
