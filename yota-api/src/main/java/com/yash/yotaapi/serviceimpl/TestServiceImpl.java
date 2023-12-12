package com.yash.yotaapi.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.TechnologyMaster;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.ParentTechnologyException;
import com.yash.yotaapi.exception.TestException;
import com.yash.yotaapi.exception.TestNotFoundException;
import com.yash.yotaapi.model.request.TestRequest;
import com.yash.yotaapi.repository.TechnologyMasterRepository;
import com.yash.yotaapi.repository.TestRepository;
import com.yash.yotaapi.service.TechnologyMasterService;
import com.yash.yotaapi.service.TestService;

/**
 * 
 * @author dimpal.gaur
 * 
 */
@Service
public class TestServiceImpl implements TestService {

	@Autowired
	private TestRepository testRepository;
	
	@Autowired
    private TechnologyMasterService technologyMasterService;
	
	@Override
	public Test saveOrUpdate(TestRequest testRequest) throws TestException {
		Test test = new Test();
		test.setId(testRequest.getId());
		test.setTitle(testRequest.getTitle());
		test.setDescription(testRequest.getDescription());
		test.setInstruction(testRequest.getInstruction());
		test.setTechnologyMaster(technologyMasterService.getTech(testRequest.getTechnologyId()));
		return testRepository.save(test);
	}


	@Override
	public Optional<Test> showTestDetailById(long id) throws TestNotFoundException {
		return testRepository.findById(id);
	}

	@Override
	public Iterable<Test> showAllTest() {
		return testRepository.findAll();
	}

	@Override
	public void deleteTestById(long id) throws TestNotFoundException{
		testRepository.deleteById(id);
	}

	@Override
	public Test updateTest(Test test) {
		return testRepository.save(test);
	}
}
