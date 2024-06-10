package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.TestEmployeeResult;
import com.yash.yotaapi.dto.TprReportDto;
import com.yash.yotaapi.dto.TrainingListDto;
import com.yash.yotaapi.entity.Trainings;
import java.util.List;

public interface ITrainingService {

	Trainings addTraining (Trainings training);

	List<TrainingListDto> listTraining ();

	Integer assignTraining(Integer trainingId, List<String> emailId);

	Integer registeredCount(Integer trainingId);

	Integer updateRegisteredCount(Integer trainingId, Integer registeredCount);

	Trainings assignedAssociated(Integer trainingId);
	List<TprReportDto> getTprReport(Integer trainingId);
	List<TestEmployeeResult> getEmployeeWiseTestDetails(Integer trainingId,Integer empId);
}
