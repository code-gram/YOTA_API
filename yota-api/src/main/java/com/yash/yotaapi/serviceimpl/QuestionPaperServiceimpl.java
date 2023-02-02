package com.yash.yotaapi.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.QuestionPaper;
import com.yash.yotaapi.repository.QuestionPaperRepository;
import com.yash.yotaapi.service.QuestionPaperService;

/**
 *  This Is Service Layer Class For Write Business Logic.
 * @author himanshu.pednekar
 */
@Service
public class QuestionPaperServiceimpl implements QuestionPaperService {
	
	@Autowired
	QuestionPaperRepository testRepository;

	@Override
	public void createTest(QuestionPaper test) {
		testRepository.save(test);
		
	}

}
