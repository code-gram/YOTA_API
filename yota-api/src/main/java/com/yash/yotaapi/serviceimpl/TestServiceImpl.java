package com.yash.yotaapi.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestException;
import com.yash.yotaapi.exception.TestNotFoundException;
import com.yash.yotaapi.repository.TestRepository;
import com.yash.yotaapi.service.TestService;

/**
 * This is service layer class for Test to write business logic
 * 
 * @author dimpal.gaur
 * 
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	/**
	 * This method is for save Test to DB through repository layer
	 */
	@Override
	public Test saveOrUpdate(Test test) throws TestException {
		return testRepository.save(test);
	}

	/**
	 * This method is for find test by id from DB through repository layer
	 */
	@Override
	public Optional<Test> findTestById(long id) throws TestNotFoundException {
		return testRepository.findById(id);
	}

	/**
	 * This method is for get list of all test from DB through repository layer
	 */
	@Override
	public Iterable<Test> findAllTest() {
		return testRepository.findAll();
	}

	/**
	 * This method is for delete test from DB through repository layer
	 */
	@Override
	public void deleteTestById(long id) throws TestNotFoundException{
		testRepository.deleteById(id);
	}

	/**
	 * This method is update from DB through repository layer
	 */
	@Override
	public Test updateTest(Test test) {
		return testRepository.save(test);
	}
}
