package com.yash.yotaapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TrainingTypeMaster;
import com.yash.yotaapi.domain.UnitMaster;

//@Service
public interface MasterService {

	public List<UnitMaster> getAllUnitDetails();

	public List<CompentencyMaster> getAllCompetencyDetails();

	public List<TrainingTypeMaster> getAllTrainingTypeDetails();
}
