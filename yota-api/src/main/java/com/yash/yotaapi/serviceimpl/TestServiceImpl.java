package com.yash.yotaapi.serviceimpl;


import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestException;
import com.yash.yotaapi.repository.TestRepository;
import com.yash.yotaapi.service.TestService;

/**
 * This is service layer class for Test Bank Management to write business
 * logic
 * 
 * @author jaie.arkadi
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;

	/**
	 * This method is for save Test to DB through repository layer
	 */

	@Override
	public Test saveOrUpdate(Test test) {

		try {
			
			test.setTestName(test.getTestName());
			test.setTestDescription(test.getTestDescription());
			test.setTechnology(test.getTechnology());
			return testRepository.save(test);

		}catch(Exception ex) {
			throw new TestException("Test : "+test.getTestName()+ " already exixts");
			

		}

	}

	/**
	 * This method is for find Test by id  from DB through repository layer
	 */
	@Override
	public Test findTestById(Long testId) {
		Test test = testRepository.findTestByTestId(testId);

		if (test==null) {
			throw new TestException("Test Id : " + testId +  " Does not exist");

		}
		return test;
	}

	/**
	 * This method is for find all Test from DB through repository layer
	 */

	@Override
	public Iterable<Test> findAllTest() {

		return testRepository.findAll();
	}

	/**
	 * This method is for delete Test from DB through repository layer
	 */

	@Override
	public void deleteTestById(Long testId) {

		Test test = testRepository.findTestByTestId(testId);

		if (test==null) {
			throw new TestException("Test Id : "+testId+ " Does not exist");

		}

		testRepository.delete(test);
	}
	
	/**
	 * This method is for update Test to DB through repository layer
	 */
	@Override
	@Transactional
	public Test updateTest(Test test) {
		Test existTest=testRepository.findTestByTestId(test.getTestId());
		if (existTest==null) {
			return testRepository.save(test);
		} else {
			existTest.setTestId(test.getTestId());
			existTest.setTestName(test.getTestName());
			existTest.setTestDescription(test.getTestDescription());
			existTest.setTechnology(test.getTechnology());
			testRepository.save(existTest);
		}
		return test;
	}


}
