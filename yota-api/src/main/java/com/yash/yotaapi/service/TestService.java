package com.yash.yotaapi.service;

import javax.validation.Valid;

import com.yash.yotaapi.domain.Test;

public interface TestService {

	void createTest(@Valid Test test);

}
