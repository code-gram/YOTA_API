package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TrainingTypeMaster;
import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.exception.NoDataFoundException;
import com.yash.yotaapi.repository.CompentencyMasterRepository;
import com.yash.yotaapi.repository.TrainingTypeMasterRepository;
import com.yash.yotaapi.repository.UnitMasterRepository;
import com.yash.yotaapi.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	UnitMasterRepository unitMasterRepository;
	
	@Autowired
	CompentencyMasterRepository compentencyMasterRepository;
	
	@Autowired
	TrainingTypeMasterRepository trainingTypeMasterRepository;
	
	@Override
	public List<UnitMaster> getAllUnitDetails() {
		List<UnitMaster> unitMasters= unitMasterRepository.findAll();
		if(unitMasters.isEmpty())
			throw new NoDataFoundException("Unit Data not found!");
		return unitMasters;
	}

	@Override
	public List<CompentencyMaster> getAllCompetencyDetails() {
		List<CompentencyMaster> compentencyMasters= compentencyMasterRepository.findAll();
		if(compentencyMasters.isEmpty())
			throw new NoDataFoundException("Competency Data Not Found!");
		return compentencyMasters;
	}

	@Override
	public List<TrainingTypeMaster> getAllTrainingTypeDetails() {
		List<TrainingTypeMaster> trainingTypeMasters= trainingTypeMasterRepository.findAll();
		if(trainingTypeMasters.isEmpty())
			throw new NoDataFoundException("Training Type Data not found!");
		return trainingTypeMasters;
	}
	
	

	
}
