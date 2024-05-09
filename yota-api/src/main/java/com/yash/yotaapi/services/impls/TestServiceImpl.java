package com.yash.yotaapi.services.impls;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.exceptions.ApplicationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.dto.TestDto;
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
        Tests existingTest = testRepository.findByTestTitle(testDto.getTestTitle());
        if (existingTest!=null) {
            throw new TestAvailableException("Test is already exist");
        }
      Tests test =  mapper.map(testDto, Tests.class);
      test=testRepository.save(test);
		Assert.notNull(test);
		return this.mapper.map(test, TestDto.class);
       
    }

	@Override
	public List<TestDto> fetchAllTest() {
		List<Tests> tests= testRepository.findAll();
		return tests.stream().map(t->this.mapper.map(t, TestDto.class)).collect(Collectors.toList());
	}

	public Long getAppearedTestCountByAssociateEmail(String email) throws ApplicationException {

		List<Long> testsId = testRepository.getTestIdByEmailId(email);
		Long appearedTestCount = 0L; // Initialize count to 0
		if (testsId.isEmpty()) {
			throw new ApplicationException("No Test assigned to the associate with email: " +email);
		}
		for (Long testId : testsId) {
			Long count = testRepository.countTestsByTestIdAndNotEmptyResultId(testId);
			appearedTestCount += count; // Add individual test counts to total
		}

		// Handle case where no tests are assigned to the user
		if (appearedTestCount == 0L) {
			return 0L; // Return 0 if no tests appeared in
		}

		return appearedTestCount;
	}


	public List<TestsDto> getTestsByAssociateEmail(String email) throws ApplicationException {
		List<Long> testIds = testRepository.getTestIdByEmailId(email);
		List<TestsDto> testsDTOs = new ArrayList<>();

		for (Long testId : testIds) {
			Tests tests = testRepository.findById(testId).orElse(null); // Fetch test by ID

			if (tests != null) {
				TestsDto testsDTO = new TestsDto();
				testsDTO.setId(tests.getId());
				testsDTO.setTestName(tests.getTestTitle());
				testsDTO.setStartDate(tests.getStartDate());
				testsDTO.setEndDate(tests.getEndDate());
				testsDTO.setAction(tests.getAction());

				testsDTOs.add(testsDTO);
			}
		}

		if (testsDTOs.isEmpty()) {
			throw new ApplicationException("No training assigned to the associate with email: " + email);
		}

		return testsDTOs;
	}




}
