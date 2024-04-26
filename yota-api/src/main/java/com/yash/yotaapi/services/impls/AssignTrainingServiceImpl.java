package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.entity.AssignTraining;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.repositories.AssignTrainingRepository;
import com.yash.yotaapi.services.IServices.IAssignTrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Objects;

@Service
public class AssignTrainingServiceImpl implements IAssignTrainingService {

    @Autowired
    AssignTrainingRepository assignTrainingRepository;

    @Override
    public AssignTraining assignTraining(AssignTraining assignTraining) {
        AssignTraining saveAssignTraining = null;

        if(Objects.isNull(assignTraining)) {
            throw new ApplicationException("Email is empty");
        } else{
            assignTraining.setCreatedAt(new Date());
            saveAssignTraining = assignTrainingRepository.save(assignTraining);
        }
        return saveAssignTraining;
    }
}