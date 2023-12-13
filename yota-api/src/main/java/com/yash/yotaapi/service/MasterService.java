package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.Competency;
import com.yash.yotaapi.domain.TrainingMaster;
import com.yash.yotaapi.domain.UnitMaster;

//@Service
public interface MasterService {

	public List<UnitMaster> getUnitDetails();

	public List<TrainingMaster> getTrainingTypeDetails();

	public List<Competency> getCompetencyDetails();
}
