package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.domain.AssociateDetailsTest;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestAlreadyExistsException;
import com.yash.yotaapi.exception.TestNotFoundException;
import com.yash.yotaapi.repository.AssociateDetailsRepository;
import com.yash.yotaapi.repository.TestRepository;
import com.yash.yotaapi.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	
	@Autowired
	private TestRepository testRepo;
	
	@Autowired
	private AssociateDetailsRepository associateDetailsRepository;

	@Override
	@Transactional
	public Test addTest(Test test) {
		return testRepo.save(test);
	}

	@Override
	public List<Test> getAllTest() {
		return testRepo.findAll();
	}

	@Override
	public void deleteTestById(Long id) {
		Optional<Test> testOptional = testRepo.findById(id);
		if (testOptional.isPresent()) {
			testRepo.deleteById(id);
		} else {
			throw new TestNotFoundException("Test Not Found!!!...");
		}

	}

	@Override
	public Test getTestById(Long id) {
		Optional<Test> testOptional = testRepo.findById(id);
		if (testOptional.isPresent()) {
			return testOptional.get();
		} else {
			throw new TestNotFoundException("Test Not Found!!!...");
		}

	}

	@Override
	public Test updateTest(Long id, Test test) {
		Optional<Test> TestDetails = testRepo.findById(id);
		if (TestDetails.isPresent()) {
			Test existingTest = TestDetails.get();
			if (!getTestByName(test.getName())) {
				existingTest.setName(test.getName());
				existingTest.setDescription(test.getDescription());
				return testRepo.save(existingTest);
			} else {
				throw new TestAlreadyExistsException("Test Already Exists...");
			}

		} else {
			throw new TestNotFoundException("Test Not Found with id:" + id);

		}
	}

	@Override
	public Boolean getTestByName(String testName) {
		Test test = testRepo.findByName(testName);

		return test != null;
	}

	@Override
	public Set<AssociateDetailsTest> getAssignedTests(Long id) {
		
		Optional<AssociateDetails> associateDetails=associateDetailsRepository.findById(id);
		if(!associateDetails.isPresent()){
			throw new EntityNotFoundException("Associate details entry not found for given Id"+id);
		}
		AssociateDetails associateDetails1=associateDetails.get();

		return  associateDetails1.getAssociateDetailsTests();
	}
	
	

}
