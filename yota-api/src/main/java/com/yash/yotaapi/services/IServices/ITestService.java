package com.yash.yotaapi.services.IServices;

import java.util.List;
import java.util.Optional;

import com.yash.yotaapi.dto.TestDto;
import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.dto.*;
import com.yash.yotaapi.entity.Tests;

public interface ITestService {
    public TestDto addTest(TestDto testDto);

    public List<TestDto> fetchAllTest();

    public List<TestsDto> getTestsByAssociateEmail(String email);

    public Optional<TestDto> findById(Long id);

    public TestsDto getTestResultByUserEmailAndTestId(String email, Long testId);

    public void assignTestToUser(Long testId, Long questionId, Long userIds);

    String addQuestionInTest(Long testId, List<Long> questionIds);

    String updateTotalQuestionCount(Integer totalQuestionCount, Long testId);

}
