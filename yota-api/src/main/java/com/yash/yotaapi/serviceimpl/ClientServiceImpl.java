package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Client;
import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.repository.ClientRespository;
import com.yash.yotaapi.service.ClientService;


@Service
public class ClientServiceImpl implements ClientService {
	@Autowired
	ClientRespository clientRespository;

	@Override
	public Client createClient(Client createClient) {
		// TODO Auto-generated method stub
		return clientRespository.save(createClient);
	}

	@Override
	public Iterable<Client> findAllQuestion() {
	
		  

		return clientRespository.findAll();
	}

	
}
