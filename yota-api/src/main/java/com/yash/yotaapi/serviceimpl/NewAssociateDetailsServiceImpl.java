package com.yash.yotaapi.serviceimpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.yash.yotaapi.domain.AssociateDetails;
import com.yash.yotaapi.domain.NewAssociateDetail;
import com.yash.yotaapi.domain.Training;
import com.yash.yotaapi.repository.NewAssociateDetailsRepository;
import com.yash.yotaapi.repository.TrainingRepository;
import com.yash.yotaapi.service.NewAssociateDetailsService;
import com.yash.yotaapi.util.ExcelHelper;

@Service
public class NewAssociateDetailsServiceImpl implements NewAssociateDetailsService{
	
	@Autowired
	private NewAssociateDetailsRepository newAssociateDetailsRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	/**
	 * This method returns all associates from db through repository layer.
	 */
	@Override
	public List<NewAssociateDetail> getAllAssociates(long id) {
		List<NewAssociateDetail> list=new ArrayList<NewAssociateDetail>();
		try {
			Training training=trainingRepository.findById(id).get();
			 list=newAssociateDetailsRepository.findByTraining(training);
	}catch (Exception e) {
		e.printStackTrace();
	}
		return list;
	}

	@Override
	@Transactional
	public void saveExcel(MultipartFile file,long id) {
		// TODO Auto-generated method st
		try {
			Training training=trainingRepository.findById(id).get();
			List<NewAssociateDetail> associate = ExcelHelper.convertExcelToListOfAssociates(file.getInputStream(),training); 
			newAssociateDetailsRepository.saveAll(associate);
			Training training1=trainingRepository.findById(id).get();
			training1.setAssociateCount(training1.getAssociateDetails().size());
			trainingRepository.save(training1);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * This method returns all associates from db through repository layer.
	 */
	@Override
	public List<NewAssociateDetail> getAllAssociates() {
		return newAssociateDetailsRepository.findAll();
	}
	
	

}
