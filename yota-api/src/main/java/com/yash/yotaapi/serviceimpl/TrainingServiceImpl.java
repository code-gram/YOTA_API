package com.yash.yotaapi.serviceimpl;

import java.util.Date;
import java.util.List;

import java.util.Optional;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.yash.yotaapi.constants.TrainingStatus;
import com.yash.yotaapi.domain.Training;
import com.yash.yotaapi.exception.TrainingIdException;
import com.yash.yotaapi.exception.*;
import com.yash.yotaapi.repository.TrainingRepository;
import com.yash.yotaapi.service.TrainingService;
import com.yash.yotaapi.util.UniqueNameGenerator;

/**
 * 
 * Implementation of TrainingServiceImpl providing business logic for Training
 * 
 * entities.* 
 * 
 * @author Pragati
 * 
 */

@Service

public class TrainingServiceImpl implements TrainingService {

    @Autowired

    private TrainingRepository trainingRepository;

    @Override

    public Training createTraining(Training training) {

       training.setTrainingName(training.getTrainingName().toUpperCase());
        String input = training.getTrainingName().toUpperCase();

        String[] parts = input.split("-");

        System.out.println(training.toString());
        System.out.println(parts.length);
        

	@Autowired
	private TrainingRepository trainingRepository;

	@Override
	public Training createTraining(Training training) {
		training.setTrainingName(training.getTrainingName().toUpperCase());
		String input = training.getTrainingName().toUpperCase();
		/**
		 * @author pragati.paliwal
		 * Training default status set as REQUESTED trainingStatus PLANNED.
		 */
		 training.setStatus(TrainingStatus.REQUESTED.toString());
	     training.setTrainingStatus(TrainingStatus.PLANNED.toString());
		String[] parts = input.split("-");
		System.out.println(parts.length);
		System.out.println(toString());

        try {

            return trainingRepository.save(training);

        } catch (DataIntegrityViolationException e) {

            throw new TrainingNotFoundException("Training with Name " + training.getTrainingName().toUpperCase() + " already exists!!");

        }

    }

    @Override

    public List<Training> getAllDetails() {

        List<Training> trainingDetails = trainingRepository.findAll();

        if (trainingDetails.isEmpty()) {

            throw new TrainingNotFoundException("Training details do not exist!!");

        }

        return trainingDetails;

    }

    @Override

    public Training getTraining(long trainingId) {

        return trainingRepository.findById(trainingId)

                .orElseThrow(() -> new TrainingIdException("Training with trainingId : " + trainingId + " does not exist"));

    }

    @Override

   public Training updateTrainingDetails(Training training, long trainingId) {

        Training trainingDetails = trainingRepository.findById(trainingId)

                .orElseThrow(() -> new TrainingNotFoundException("Training trainingId: " + trainingId + " is not present"));

        trainingDetails.setTrainingName(training.getTrainingName());

        trainingDetails.setTrainingDescription(training.getTrainingDescription());

        trainingDetails.setStartDate(training.getStartDate());

        trainingDetails.setEndDate(training.getEndDate());

        return trainingRepository.save(trainingDetails);

   }
    

    @Override

    public void removeTrainingDetails(long trainingId) {

        if (!trainingRepository.existsById(trainingId)) {

            throw new TrainingNotFoundException("Training trainingId: " + trainingId + " is not present");

        }

        trainingRepository.deleteById(trainingId);

    }

    @Override

    public List<Training> searchTraining(String keyword) {

        List<Training> search = trainingRepository.findByTrainingNameContaining(keyword.toUpperCase());

        if (search.isEmpty()) {

            throw new TrainingNotFoundException("Training containing keyword : " + keyword + " does not exist");

        }

        return search;

    }

    @Override

    public List<Training> getByStartDateAndEndDate(Date startDate, Date endDate) {

        long diff = endDate.getTime() - startDate.getTime();

        long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

        if (dayDiff < 1) {

            throw new DateInValidException("End date should be greater than start date");

        }

        List<Training> search = trainingRepository.findByDateBetween(startDate, endDate);

        if (search.isEmpty()) {

            throw new TrainingNotFoundException(

                    "Training containing startDate : " + startDate + " and endDate : " + endDate + " does not exist");

        }

        return search;

    }

    @Override
    public void updateStatusOnTrainingReject(Long trainingId,String action,String rejectMessage) {
    	
		Training trainingDetails= trainingRepository.findById(trainingId).get();
		if(action.equals("Reject")) {
			trainingDetails.setStatus(TrainingStatus.REJECTED.toString());
			trainingDetails.setTrainingStatus(TrainingStatus.TERMINATED.toString());
			trainingDetails.setRejectTrainingMessage(rejectMessage);
			trainingRepository.save(trainingDetails);
		}
			
    	
    }


		try {
			return trainingRepository.save(training);
		} catch (DataIntegrityViolationException e) {
			throw new TrainingNotFoundException(
					"Training with Name " + training.getTrainingName().toUpperCase() + " already exists!!");
		}
	}

	@Override
	public List<Training> getAllDetails() {
		List<Training> trainingDetails = trainingRepository.findAll();
		if (trainingDetails.isEmpty()) {
			throw new TrainingNotFoundException("Training details do not exist!!");
		}
		return trainingDetails;
	}

	@Override
	public Training getTraining(long trainingId) {
		return trainingRepository.findById(trainingId).orElseThrow(
				() -> new TrainingIdException("Training with trainingId : " + trainingId + " does not exist"));
	}

	@Override
	public Training updateTrainingDetails(Training training, long trainingId) {
		Training trainingDetails = trainingRepository.findById(trainingId).orElseThrow(
				() -> new TrainingNotFoundException("Training trainingId: " + trainingId + " is not present"));
		trainingDetails.setTrainingName(training.getTrainingName());
		trainingDetails.setTrainingDescription(training.getTrainingDescription());
		trainingDetails.setStartDate(training.getStartDate());
		trainingDetails.setEndDate(training.getEndDate());
		return trainingRepository.save(trainingDetails);
	}

	@Override
	public Training updateActualStartAndEndDate(Training training, long trainingId) {
		Training trainingDetails = trainingRepository.findById(trainingId).orElseThrow(
				() -> new TrainingNotFoundException("Training trainingId: " + trainingId + " is not present"));
		trainingDetails.setActualStartDate(training.getActualStartDate());
		trainingDetails.setActualEndDate(training.getActualEndDate());
		trainingDetails.setTrainerName(training.getTrainerName());
		trainingDetails.setStatus(TrainingStatus.APPROVED.toString());
		trainingDetails.setTrainingStatus(TrainingStatus.INPROGRESS.toString());
		return trainingRepository.save(trainingDetails);
	}
	@Override

	public void removeTrainingDetails(long trainingId) {
		if (!trainingRepository.existsById(trainingId)) {
			throw new TrainingNotFoundException("Training trainingId: " + trainingId + " is not present");
		}
		trainingRepository.deleteById(trainingId);
	}

	@Override
	public List<Training> searchTraining(String keyword) {
		List<Training> search = trainingRepository.findByTrainingNameContaining(keyword.toUpperCase());
		if (search.isEmpty()) {
			throw new TrainingNotFoundException("Training containing keyword : " + keyword + " does not exist");
		}
		return search;
	}

	@Override

	public List<Training> getByStartDateAndEndDate(Date startDate, Date endDate) {
		long diff = endDate.getTime() - startDate.getTime();
		long dayDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		if (dayDiff < 1) {
			throw new DateInValidException("End date should be greater than start date");
		}
		List<Training> search = trainingRepository.findByDateBetween(startDate, endDate);
		if (search.isEmpty()) {
			throw new TrainingNotFoundException(
					"Training containing startDate : " + startDate + " and endDate : " + endDate + " does not exist");
		}
		return search;
	}

	@Override
	public Boolean updateStatusOfTraining(Long trainingId, String action) {
		Training trainingDetails = trainingRepository.findById(trainingId).get();
		if (action.equalsIgnoreCase("Reject")) {
			trainingDetails.setStatus(TrainingStatus.REJECTED.toString());
			trainingDetails.setStatus(TrainingStatus.TERMINATED.toString());
			Training training = trainingRepository.save(trainingDetails);
			if (training != null) {
				return true;
			}
		}
		if (action.equalsIgnoreCase("Approve")) {
			trainingDetails.setStatus(TrainingStatus.APPROVED.toString());
			trainingDetails.setTrainingStatus(TrainingStatus.INPROGRESS.toString());
			Training training = trainingRepository.save(trainingDetails);
			if (training != null) {
				return true;
			}
		}
		return false;
	}

}