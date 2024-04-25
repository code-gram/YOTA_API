package com.yash.yotaapi.services.IServices;

import java.util.List;

import com.yash.yotaapi.entity.Trainings;

public interface ITrainingService {

	public Trainings addTraining (Trainings training);
	public List<Trainings> listTraining ();
}
