package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.CreateClient;
import com.yash.yotaapi.domain.CreateClientQuestion;
import com.yash.yotaapi.repository.CreateClientRespository;
import com.yash.yotaapi.service.CreateClientService;


@Service
public class CreateClientServiceImpl implements CreateClientService {
	@Autowired
	CreateClientRespository clientRespository;

	@Override
	public CreateClient createClient(CreateClient createClient) {
		// TODO Auto-generated method stub
		return clientRespository.save(createClient);
	}

	@Override
	public Iterable<CreateClient> findAllQuestion() {
	
		  

		return clientRespository.findAll();
	}

	
}
