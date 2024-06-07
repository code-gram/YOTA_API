package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.UserTrainingTest;
import com.yash.yotaapi.entity.YotaUser;
import com.yash.yotaapi.exceptions.TrainingException;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.repositories.TrainingRepository;
import com.yash.yotaapi.repositories.UserTrainingTestRepository;
import com.yash.yotaapi.services.IServices.IUserTrainingTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserTrainingTestServiceImpl implements IUserTrainingTestService {

    @Autowired
     private TrainingRepository trainingRepository;
    @Autowired
     private TestRepository testRepository;

    @Autowired
    private UserTrainingTestRepository userTrainingTestRepository;

    @Override
    public void assignTest(Long trainingId, Long testId) {

        Trainings training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingException("Training is not available for trainingId :-" + trainingId, HttpStatus.BAD_REQUEST));

        Tests test = testRepository.findById(testId)
                .orElseThrow(() -> new TrainingException("Test is not available for testId :-" + testId, HttpStatus.BAD_REQUEST));

        boolean alreadyAssigned = userTrainingTestRepository.existsByTrainingsIdAndTestId(trainingId, testId);

        if (alreadyAssigned) {
            throw new TrainingException("Test is already assigned to training.", HttpStatus.BAD_REQUEST);
        }

        List<UserTrainingTest> userTrainingTests = new ArrayList<>();
        for (YotaUser user : training.getAssign()) {
            UserTrainingTest userTrainingTest = new UserTrainingTest();
            userTrainingTest.setUser(user);
            userTrainingTest.setTrainings(training);
            userTrainingTest.setTest(test);
            userTrainingTests.add(userTrainingTest);
        }

        userTrainingTestRepository.saveAll(userTrainingTests);
    }
}
