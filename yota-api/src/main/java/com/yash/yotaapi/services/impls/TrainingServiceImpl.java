package com.yash.yotaapi.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.repositories.TrainingRepository;
import com.yash.yotaapi.services.IServices.ITrainingService;

@Service
public class TrainingServiceImpl implements ITrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Override
	public Trainings addTraining(Trainings training) {
		return trainingRepository.save(training);
	}

	@Override
	public List<Trainings> listTraining() {
		return trainingRepository.findAll();
	}
}
