package com.yash.yotaapi.services.IServices;

import java.util.List;

import com.yash.yotaapi.dto.*;

public interface ITestService {
	public TestDto addTest(TestDto testDto);

	public List<TestDto> fetchAllTest();

	public List<TestsDto> getTestsByAssociateEmail(String email);

	public TestsDto getTestResultByUserEmailAndTestId(String email, Long testId);

}
