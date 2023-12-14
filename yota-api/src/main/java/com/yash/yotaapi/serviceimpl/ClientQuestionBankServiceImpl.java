package com.yash.yotaapi.serviceimpl;

import java.io.IOException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.ClientQuestion;
import com.yash.yotaapi.exception.QuestionException;
import com.yash.yotaapi.exception.TrainingIdException;
import com.yash.yotaapi.repository.ClientQuestionRepository;
import com.yash.yotaapi.service.ClientQuestionBankService;
import com.yash.yotaapi.util.ExcelHelper;

@Service
public class ClientQuestionBankServiceImpl implements ClientQuestionBankService {

	@Autowired
	ClientQuestionRepository createClientQuestionRepository;

	@Override
	public List<ClientQuestion> saveall(List<ClientQuestion> question) {

		return createClientQuestionRepository.saveAll(question);
	}

	@Override
	public Iterable<ClientQuestion> findQuestion() {

		return createClientQuestionRepository.findAll();
	}

	@Override
	public void saveAll(MultipartFile file) {
		try {

			List<ClientQuestion> questions = ExcelHelper.convertExcelToListOfClientQuestion(file.getInputStream());
			createClientQuestionRepository.saveAll(questions);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}

	}

	@Override
	public ClientQuestion saveOrUpdate(ClientQuestion question) {
		
		try {
			return createClientQuestionRepository.save(question);

		} catch (Exception ex) {
			throw new QuestionException("Question : " + question.getClientQuestion() + " already exixts");
		}
	}

	@Override
	public List<ClientQuestion> getClientQuestions(long clientId) {
		
		List<ClientQuestion> detail = createClientQuestionRepository.findByClientId(clientId + "");
		if (detail == null) {
			throw new TrainingIdException("ClientQuestion  with id : " + clientId + " does not exist");
		}
		return detail;
	}
}
