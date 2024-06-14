package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.TestResultDto;
import com.yash.yotaapi.entity.*;
import com.yash.yotaapi.exceptions.ResourceNotFoundException;
import com.yash.yotaapi.repositories.*;
import com.yash.yotaapi.services.IServices.ITestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TestResultServiceImpl implements ITestResultService {

    @Autowired
    private TestResultRepository testResultRepository;

    @Autowired
    private TestRepository testRepository;

    @Autowired
    private YotaUserRepository yotaUserRepository;

    public TestResult saveTestResult(TestResultDto testResultDto) {
        Optional<Tests> test = testRepository.findById(testResultDto.getTestId());
        if (!test.isPresent()) {
            throw new ResourceNotFoundException("Invalid test ID provided.");
        }

        YotaUser yotaUser = yotaUserRepository.getUserByEmail(testResultDto.getUserEmailId());
        if (yotaUser == null) {
            throw new ResourceNotFoundException("User not found with email: " + testResultDto.getUserEmailId());
        }

        TestResult testResult=new TestResult();
        testResult.setUser(yotaUser);
        testResult.setTest(test.get());
        testResult.setResult(testResultDto.getResult());

        TestResult testResult1= testResultRepository.save(testResult);

        return testResult1;
    }

    @Override
    public TestResult updateTestResult(TestResultDto testResultDto) {
        YotaUser yotaUser = yotaUserRepository.getUserByEmail(testResultDto.getUserEmailId());
        if (yotaUser == null) {
            throw new ResourceNotFoundException("User not found with email: " + testResultDto.getUserEmailId());
        }
        TestResult existingTestResult = testResultRepository.findByTestIdAndUserIdAndResult(testResultDto.getTestId(), yotaUser.getEmpId(),testResultDto.getResult())
       .orElseThrow(() -> new ResourceNotFoundException("Test result not found for test ID: " + testResultDto.getTestId() + " and user ID: " + yotaUser.getEmpId()));

        existingTestResult.setStartTime(testResultDto.getStartTime());
        existingTestResult.setEndTime(testResultDto.getEndTime());
        existingTestResult.setTimeTaken(testResultDto.getTimeTaken());

        return testResultRepository.save(existingTestResult);

    }


}
