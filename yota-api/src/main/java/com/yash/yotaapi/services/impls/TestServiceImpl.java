package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.TestsDto;
import com.yash.yotaapi.entity.Test;
import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.TestAvailableException;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.services.IServices.ITestService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TestServiceImpl implements ITestService {

    @Autowired
    private TestRepository testRepository;


    @Autowired
    private ModelMapper mapper;

    @Override
    public String addTest(Test test) {
        // Check if the test already exists
        Test existingTest = testRepository.findByTitle(test.getTitle());
        if (existingTest != null) {
            throw new TestAvailableException("Test is already exist");
        }
        test = testRepository.save(test);
        return "Test details added successfully";
    }

//    @Override
//    public List<TestDto> fetchAllTest() {
//        List<Tests> tests = testRepository.findAll();
//        return tests.stream().map(t -> this.mapper.map(t, TestDto.class)).collect(Collectors.toList());
//    }
//
//    @Override
//    public Optional<TestDto> findById(Long id) {
//        Optional<Tests> tests = testRepository.findById(id);
//        TestDto testDto = new TestDto();
//        tests.ifPresent((test) -> {
//            testDto.setId(test.getId());
//            testDto.setTestName(test.getTestName());
//            testDto.setDescription(test.getTestDescription());
//            testDto.setInstruction(test.getTestInstruction());
//            testDto.setTestType(test.getTestType());
//            testDto.setTotalQuestions(test.getTotalQuestions());
//            testDto.setTotalTime(test.getTotalTime());
//        });
//
//        return Optional.of(testDto);
//    }

//    public Long getAppearedTestCountByAssociateEmail(String email) throws ApplicationException {
//
//        List<Long> testsId = testRepository.getTestIdByEmailId(email);
//        Long appearedTestCount = 0L; // Initialize count to 0
//        if (testsId.isEmpty()) {
//            throw new ApplicationException("No Test assigned to the associate with email: " + email);
//        }
//        for (Long testId : testsId) {
//            Long count = testRepository.countTestsByTestIdAndNotEmptyResultId(testId);
//            appearedTestCount += count; // Add individual test counts to total
//        }
//
//        // Handle case where no tests are assigned to the user
//        if (appearedTestCount == 0L) {
//            return 0L; // Return 0 if no tests appeared in
//        }
//
//        return appearedTestCount;
//    }

//    public List<TestsDto> getTestsByAssociateEmail(String email) throws ApplicationException {
//        List<Long> testIds = testRepository.getTestIdByEmailId(email);
//        List<TestsDto> testsDTOs = new ArrayList<>();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        for (Long testId : testIds) {
//            Optional<Tests> optionalTests = testRepository.findById(testId); // Fetch test by ID
//            optionalTests.ifPresent(tests -> {
//                TestsDto testsDTO = new TestsDto();
//                testsDTO.setId(tests.getId());
//                testsDTO.setTestTitle(tests.getTestTitle());
//                testsDTO.setTestDescription(tests.getTestDescription());
//                testsDTO.setTestInstruction(tests.getTestInstruction());
//                testsDTO.setAction(tests.getAction());
//                testsDTO.setStartDate(tests.getStartDate().format(formatter));
//                testsDTO.setEndDate(tests.getEndDate().format(formatter));
//                testsDTO.setCreated_at(tests.getCreated_at().format(formatter));
//                testsDTO.setModified_at(tests.getModified_at().format(formatter));
//                testsDTO.setEndTime(tests.getEndTime());
//                testsDTO.setResult(tests.getResult());
//                testsDTO.setTestType(tests.getTestType());
//                testsDTOs.add(testsDTO);
//            });
//        }
//
//        if (testsDTOs.isEmpty()) {
//            throw new ApplicationException("No training assigned to the associate with email: " + email);
//        }
//        return testsDTOs;
//    }

//    public TestsDto getTestResultByUserEmailAndTestId(String email, Long testId) throws ApplicationException {
//        Optional<Tests> optionalTest = testRepository.findByTestIdAndUserEmail(testId, email);
//
//        if (!optionalTest.isPresent()) {
//            throw new ApplicationException("No test found for the given email and test ID");
//        }
//
//        Tests tests = optionalTest.get();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        TestsDto testsDTO = new TestsDto();
//        testsDTO.setId(tests.getId());
//        testsDTO.setTestTitle(tests.getTestTitle());
//        testsDTO.setTestDescription(tests.getTestDescription());
//        testsDTO.setTestInstruction(tests.getTestInstruction());
//        testsDTO.setAction(tests.getAction());
//        testsDTO.setStartDate(tests.getStartDate().format(formatter));
//        testsDTO.setEndDate(tests.getEndDate().format(formatter));
//        testsDTO.setCreated_at(tests.getCreated_at().format(formatter));
//        testsDTO.setModified_at(tests.getModified_at().format(formatter));
//        testsDTO.setEndTime(tests.getEndTime());
//        testsDTO.setResult(tests.getResult());
//        testsDTO.setTestType(tests.getTestType());
//
//        return testsDTO;
//    }
}
