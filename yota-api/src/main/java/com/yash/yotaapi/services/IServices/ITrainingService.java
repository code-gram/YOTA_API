package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.YotaUser;
import java.util.List;

public interface ITrainingService {

	Trainings addTraining (Trainings training);

	List<Trainings> listTraining ();

	Integer assignTraining(Integer trainingId, List<String> emailId);

	Integer registeredCount(Integer trainingId);

	Integer updateRegisteredCount(Integer trainingId, Integer registeredCount);

	List<YotaUser> assignedAssociated(Integer trainingId);
}
