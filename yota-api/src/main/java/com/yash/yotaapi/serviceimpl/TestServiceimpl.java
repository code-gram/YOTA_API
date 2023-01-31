package com.yash.yotaapi.serviceimpl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.repository.TestRepository;
import com.yash.yotaapi.service.TestService;

@Service
public class TestServiceimpl implements TestService {
	
	@Autowired
	TestRepository testRepository;

	@Override
	public void createTest(Test test) {
		testRepository.save(test);
		
	}

}
