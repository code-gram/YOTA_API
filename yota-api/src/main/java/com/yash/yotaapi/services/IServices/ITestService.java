package com.yash.yotaapi.services.IServices;

import java.util.List;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.dto.TrainingsDto;

public interface ITestService {
	public TestDto addTest(TestDto testDto);

	public List<TestDto> fetchAllTest();

	public List<TestsDto> getTestsByAssociateEmail(String email);


}
