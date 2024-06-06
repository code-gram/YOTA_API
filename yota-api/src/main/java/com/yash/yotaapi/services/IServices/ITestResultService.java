package com.yash.yotaapi.services.IServices;


import com.yash.yotaapi.dto.TestResultDto;
import com.yash.yotaapi.entity.TestResult;

public interface ITestResultService {

    public TestResult saveTestResult(TestResultDto testResultDto);

    }
