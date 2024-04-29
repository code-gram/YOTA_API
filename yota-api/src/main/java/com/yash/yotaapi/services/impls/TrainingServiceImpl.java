package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.YotaUser;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.repositories.TrainingRepository;
import com.yash.yotaapi.repositories.YotaUserRepository;
import com.yash.yotaapi.services.IServices.ITrainingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TrainingServiceImpl implements ITrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private YotaUserRepository yotaUserRepository;

    @Override
    public Trainings addTraining(Trainings training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<Trainings> listTraining() {
        return trainingRepository.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer assignTraining(Integer trainingId, List<String> emailId) {
        final List<Integer> resultList = new ArrayList<>();
        Integer registerCounts = 0;
        if (CollectionUtils.isEmpty(emailId)) {
            throw new ApplicationException("Email is empty");
        } else {
            emailId.forEach(email -> {
                Integer added = trainingRepository.addAssignTraining(trainingId, email);
                resultList.add(added);
            });

            if (!CollectionUtils.isEmpty(resultList)) {
                registerCounts = registeredCount(trainingId);
            }
        }
        return registerCounts;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer registeredCount(Integer trainingId) {
        if (ObjectUtils.isNotEmpty(trainingId)) {
            Integer registeredCount = trainingRepository.registeredCount(trainingId);
            log.info("registered count : " + registeredCount);
            if (registeredCount > 0) {
                updateRegisteredCount(trainingId, registeredCount);
            }
            return registeredCount;
        } else {
            throw new ApplicationException("Training id is empty");
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer updateRegisteredCount(Integer trainingId, Integer registeredCount) {
        if (ObjectUtils.isNotEmpty(trainingId)) {
            Integer updateRegisteredCount = trainingRepository.updateRegisteredCount(trainingId, registeredCount);
            log.info("updated count : " + updateRegisteredCount);
            return updateRegisteredCount;
        } else {
            throw new ApplicationException("Training id is empty");
        }
    }

    public List<YotaUser> assignedAssociated(Integer trainingIds) {
        final List<YotaUser> yotaUserList = new ArrayList<>();
        final List<Object[]> trainings = trainingRepository.assignedAssociated(trainingIds);
        trainings.forEach(email -> {
            YotaUser userByEmail = yotaUserRepository.getUserByEmail(String.valueOf(email[1]));
            yotaUserList.add(userByEmail);
        });
        return yotaUserList;
    }
}
