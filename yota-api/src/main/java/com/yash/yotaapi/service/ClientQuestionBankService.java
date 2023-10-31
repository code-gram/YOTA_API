package com.yash.yotaapi.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.ClientQuestion;

public interface ClientQuestionBankService {

	public ClientQuestion saveOrUpdate(ClientQuestion question);

	List<ClientQuestion> saveall(List<ClientQuestion> question);

	public Iterable<ClientQuestion> findAllQuestion();

	public void saveAll(MultipartFile file);

	public List<ClientQuestion> getClientQuestions(long clientId);

}
