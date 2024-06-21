package com.yash.yotaapi.services.impls;

import com.yash.yotaapi.dto.TestEmployeeResult;
import com.yash.yotaapi.dto.TprReportDto;
import com.yash.yotaapi.dto.TrainingListDto;
import com.yash.yotaapi.dto.TrainingsDto;
import com.yash.yotaapi.entity.Tests;
import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.UserTrainingTest;
import com.yash.yotaapi.entity.YotaUser;
import com.yash.yotaapi.exceptions.ApplicationException;
import com.yash.yotaapi.exceptions.TrainingException;
import com.yash.yotaapi.repositories.TestRepository;
import com.yash.yotaapi.repositories.TrainingRepository;
import com.yash.yotaapi.repositories.UserTrainingTestRepository;
import com.yash.yotaapi.repositories.YotaUserRepository;
import com.yash.yotaapi.services.IServices.ITrainingService;
import com.yash.yotaapi.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrainingServiceImpl implements ITrainingService {

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private YotaUserRepository yotaUserRepository;

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private UserTrainingTestRepository userTrainingTestRepository;

    @Override
    public Trainings addTraining(Trainings training) {
        return trainingRepository.save(training);
    }

    @Override
    public List<TrainingListDto> listTraining() {
        List<TrainingListDto> trainingListDtoList = new ArrayList<>();
        List<Trainings> trainingsList = trainingRepository.findAll();
        trainingsList.forEach(trainings -> {
            if (Objects.nonNull(trainings)) {
                TrainingListDto trainingListDto = new TrainingListDto();
                trainingListDto.setId(trainings.getId());
                trainingListDto.setTrainingName(trainings.getTrainingName());
                trainingListDto.setAssignTo(trainings.getAssignTo());
                trainingListDto.setStartDate(trainings.getStartDate());
                trainingListDto.setEndDate(trainings.getEndDate());
                trainingListDto.setStatus(trainings.getStatus());
                trainingListDto.setTotalNominations(trainings.getTotalNominations());
                trainingListDto.setRegisteredInTraining(trainings.getRegisteredInTraining());

                trainingListDtoList.add(trainingListDto);
            }
        });
        return trainingListDtoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer assignTraining(Integer trainingId, List<String> emailId) {
        final List<String> alreadyRegisteredEmails = new ArrayList<>();
        List<YotaUser> usersToAdd = new ArrayList<>();
        Integer registeredCount = 0;
        if (CollectionUtils.isEmpty(emailId)) {
            throw new ApplicationException("Email list is empty");
        }

        for (String email : emailId) {
            if (trainingRepository.existsByTrainingsIdAndEmail(trainingId, email)>0) {
                alreadyRegisteredEmails.add(email);
            } else {
                trainingRepository.addAssignTraining(trainingId, email);
                registeredCount++;
            }
        }

        if (!alreadyRegisteredEmails.isEmpty()) {
            throw new ApplicationException("Following users are already assigned to the training: " + alreadyRegisteredEmails);
        }
        registeredCount = registeredCount(trainingId);
        return registeredCount;
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

    public Trainings assignedAssociated(Integer trainingIds) {
        Trainings training = null;
        final List<YotaUser> yotaUserList = new ArrayList<>();
        final List<Object[]> trainings = trainingRepository.assignedAssociated(trainingIds);
        if (CollectionUtils.isEmpty(trainings)) {
            throw new ApplicationException("Training hasn't been assigned yet");
        } else {
            training = trainingRepository.findById(trainingIds.longValue()).get();
            trainings.forEach(email -> {
                YotaUser userByEmail = yotaUserRepository.getUserByEmail(String.valueOf(email[1]));
                yotaUserList.add(userByEmail);
            });
            training.setAssign(yotaUserList);
        }
        return training;
    }

    @Override
    public void assignTestTraining(Long testId, Long trainingId) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        Trainings training = trainingRepository.findById(trainingId)
                .orElseThrow(() -> new TrainingException("Training is not available for trainingId :-" + trainingId, HttpStatus.BAD_REQUEST));

        Tests test = testRepository.findById(testId)
                .orElseThrow(() -> new TrainingException("Test is not available for testId :-" + testId, HttpStatus.BAD_REQUEST));

        boolean alreadyAssigned = userTrainingTestRepository.existsByTrainingsIdAndTestId(trainingId, testId);

        if (alreadyAssigned) {
            throw new TrainingException("Test is already assigned to training.", HttpStatus.BAD_REQUEST);
        }

        List<UserTrainingTest> userTrainingTests = new ArrayList<>();
        int totalCount = 0;
        if (training.getAssign().isEmpty()) {
            throw new TrainingException("No users are assigned to this training. Cannot assign test.", HttpStatus.BAD_REQUEST);
        } else {
            for (YotaUser user : training.getAssign()) {
                Tests t = new Tests();
                UserTrainingTest userTrainingTest = new UserTrainingTest();
                userTrainingTest.setUser(user);
                userTrainingTest.setTrainings(training);
                userTrainingTest.setTest(test);
                userTrainingTests.add(userTrainingTest);
                Integer count = trainingRepository.countAssociateToAddedTraining(testId);
                totalCount = count + atomicInteger.getAndIncrement();
            }
            testRepository.updateTotalAssociateCount(totalCount, testId);
            userTrainingTestRepository.saveAll(userTrainingTests);
        }
    }

    public List<TrainingsDto> getTrainingByAssociateEmail(String email) throws ApplicationException {

        List<Long> trainingIds = trainingRepository.getTrainingIdByEmailId(email);
        List<TrainingsDto> trainingDTOs = new ArrayList<>();
        for (Long trainingId : trainingIds) {
            Trainings training = trainingRepository.getTrainingById(trainingId);
            if (training != null) {
                TrainingsDto trainingDTO = new TrainingsDto();
                trainingDTO.setId(training.getId());
                trainingDTO.setTrainingName(training.getTrainingName());
                trainingDTO.setStartDate(DateUtil.convertDateToLocalDateTime(training.getStartDate()));
                trainingDTO.setEndDate(DateUtil.convertDateToLocalDateTime(training.getEndDate()));
                trainingDTO.setStatus(training.getStatus());
                trainingDTOs.add(trainingDTO);
            }
        }
        if (trainingDTOs.isEmpty()) {
            throw new ApplicationException("No training assigned to the associate with email: " + email);
        }
        return trainingDTOs;
    }

    public Set<Map<String, Object>> getAllAssignedTraining() {
        List<?> allAssignedTraining = trainingRepository.getAllAssignedTraining();
        return allAssignedTraining.stream()
                .map(training -> {
                    Map<String, Object> mapList = new HashMap<>();
                    Object[] row = (Object[]) training;
                    mapList.put("email", row[1]);
                    YotaUser userByEmail = yotaUserRepository.getUserByEmail((String) mapList.get("email"));
                    mapList.put("userId", userByEmail.getEmpId());
                    mapList.put("userName", userByEmail.getFullName());
                    return mapList;
                }).collect(Collectors.toSet());
    }


    @Override
 	public List<TprReportDto> getTprReport(Integer trainingId) {
 		// TODO Auto-generated method stub
 		
 		Function<Object[],TprReportDto> myfun=(f)-> {
 			
 			
 			TprReportDto tpr=new TprReportDto();
 			tpr.setTid(trainingId);
 			if(f[1]!=null)
 			tpr.setTrainingName(f[1].toString());
 			if(f[2]!=null)
 			tpr.setEmailId(f[2].toString());
 			if(f[3]!=null)
 			tpr.setEmpName(f[3].toString());
 			if(f[4]!=null)
 			tpr.setEmployeeId(Integer.parseInt(f[4].toString()));
 			if(f[5]!=null)
 			tpr.setAvgPercentageMarks(Double.valueOf(f[5].toString()));
 						
 			return tpr;
 		};
 		
 		return trainingRepository.getTprReport(trainingId).stream().map(myfun).collect(Collectors.toList());
 		
 	}
 
 	@Override
 	public List<TestEmployeeResult> getEmployeeWiseTestDetails(Integer trainingId, Integer empId) {
 		// TODO Auto-generated method stub
 		//TestEmployeeResult r;
 		Function<Object[],TestEmployeeResult> myfun=(f)->{
 			System.out.println(f);
 			TestEmployeeResult r=new TestEmployeeResult();
 			if(f[0]!=null)r.setTestName(f[0].toString());
 			if(f[1]!=null)r.setEmpName(f[1].toString());
 			if(f[2]!=null)r.setMarks(Integer.parseInt(f[3].toString()));
 			if(f[3]!=null)r.setMarksinPercentage(Double.valueOf(f[4].toString()));
 			
 			
 			return r;};		
 		return trainingRepository.getEmployeeWiseTestReport(trainingId, empId).stream().map(myfun).collect(Collectors.toList());
 	}

}
