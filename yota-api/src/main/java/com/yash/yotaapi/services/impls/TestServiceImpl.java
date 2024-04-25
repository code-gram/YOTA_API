package com.yash.yotaapi.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.entity.Technology;
import com.yash.yotaapi.entity.Test;
import com.yash.yotaapi.exceptions.TestAvailableException;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.services.IServices.ITestService;

import io.jsonwebtoken.lang.Assert;

@Service
public class TestServiceImpl implements ITestService {
	
	@Autowired
	private TestRepository testRepository;
	
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public TestDto addTest(TestDto testDto) {
		 // Check if the test already exists
        Test existingTest = testRepository.findByTestName(testDto.getTestName());
        if (existingTest!=null) {
            throw new TestAvailableException("Test is already exist");
        }
      Test test =  mapper.map(testDto, Test.class);
      test=testRepository.save(test);
		Assert.notNull(test);
		return this.mapper.map(test, TestDto.class);
       
    }

	@Override
	public List<TestDto> fetchAllTest() {
		List<Test> tests= testRepository.findAll();
		return tests.stream().map(t->this.mapper.map(t, TestDto.class)).collect(Collectors.toList());
	}
	

}
