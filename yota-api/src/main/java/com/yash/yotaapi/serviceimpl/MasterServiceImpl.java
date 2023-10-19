package com.yash.yotaapi.serviceimpl;

import java.util.List;

import com.yash.yotaapi.domain.TechnologyMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.CompentencyMaster;
import com.yash.yotaapi.domain.TrainingMaster;
import com.yash.yotaapi.domain.UnitMaster;
import com.yash.yotaapi.exception.NoDataFoundException;
import com.yash.yotaapi.repository.CompentencyMasterRepository;
import com.yash.yotaapi.repository.TrainingMasterRepository;
import com.yash.yotaapi.repository.UnitMasterRepository;
import com.yash.yotaapi.service.MasterService;

@Service
public class MasterServiceImpl implements MasterService{

	@Autowired
	UnitMasterRepository unitMasterRepository;
	
	@Autowired
	CompentencyMasterRepository compentencyMasterRepository;
	
	@Autowired
	TrainingMasterRepository trainingMasterRepository;
	
	@Override
	public List<UnitMaster> getUnitDetails() {
		List<UnitMaster> unitMasters= unitMasterRepository.findAll();
		if(unitMasters.isEmpty())
			throw new NoDataFoundException("Unit Data not found!");
		return unitMasters;
	}

	@Override
	public List<CompentencyMaster> getCompetencyDetails(){
		List<CompentencyMaster> compentencyMasters= compentencyMasterRepository.findAll();
		if(compentencyMasters.isEmpty())
			throw new NoDataFoundException("Competency Data Not Found!");
		return compentencyMasters;
	}

	@Override
	public List<TrainingMaster> getTrainingTypeDetails() {
		List<TrainingMaster> trainingTypeMasters= trainingMasterRepository.findAll();
		if(trainingTypeMasters.isEmpty())
			throw new NoDataFoundException("Training Type Data not found!");
		return trainingTypeMasters;
	}


    /**
     * ParentTechnologyService will be performing business logic related to ParentTechnology
     * @author pankaj.ssharma
     *
     */
    public static interface TechnologyMasterService {

        /**
         * save method will save the ParentTechnology. If same technology is already available then it should throw relevant exception.
         * @param technology to be saved
         * @return Saved ParentTechnology, it should hold the id of the new ParentTechnology saved in DB
         */
        TechnologyMaster save(TechnologyMaster technology);

        /**
         * getAllTechs method will fetch all ParentTechnologies from DB
         * @return List of ParentTechnology
         */
        List<TechnologyMaster> getAllTechs();

        /**
         * removeTech method will remove the technology that is mentioned
         * @param id of the ParentTechnology to be removed
         *  true if removed, otherwise false, in case technology is not available then it should throw appropriate exception.
         */
        void removeTech(long id);

        /**
         * this updateTech method will update the technology detail of the existing technology
         * @param technology to be updated
         * @return Updated ParentTechnology
         */
        TechnologyMaster updateTech(TechnologyMaster technology);

        /**
         * this getTech method will get the technology detail from
         * DB based on its name only
         * @return ParentTechnolgy
         */
        TechnologyMaster getTech(String name);

        /**
         * This searchTech enables us to search free text get the list of technologies
         * @param keyword in upper case
         * @return if there are any ParentTechnology by that keyword
         */
        List<TechnologyMaster> searchTech(String keyword);

    }
}
