package com.yash.yotaapi.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.yash.yotaapi.domain.ClientQuestion;

public interface ClientQuestionBankService {

	public ClientQuestion saveOrUpdate(ClientQuestion question);

	List<ClientQuestion> saveall(List<ClientQuestion> question);

	public Iterable<ClientQuestion> findAllQuestion();

	public void saveAll(MultipartFile file);
}
