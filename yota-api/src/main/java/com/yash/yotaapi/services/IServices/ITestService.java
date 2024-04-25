package com.yash.yotaapi.services.IServices;

import java.util.List;

import com.yash.yotaapi.dto.TechnologyDto;
import com.yash.yotaapi.dto.TestDto;

public interface ITestService {
	public TestDto addTest(TestDto testDto);

	public List<TestDto> fetchAllTest();


}
