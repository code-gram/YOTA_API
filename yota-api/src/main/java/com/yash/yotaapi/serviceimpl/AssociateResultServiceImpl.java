package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.AssociateResults;
import com.yash.yotaapi.repository.AssociateResultRepository;
import com.yash.yotaapi.service.AssociateResultservice;
;

@Service
public class AssociateResultServiceImpl implements AssociateResultservice {
	private final AssociateResultRepository associateResultRepository;

    AssociateResultServiceImpl(AssociateResultRepository associateResultRepository) {
        this.associateResultRepository = associateResultRepository;
    }

    @Override
    public AssociateResults getAssociateResultsBYId(Long resultId) {
        return associateResultRepository.findById(resultId).orElse(null);
    }

    @Override
    public List<AssociateResults> getAllAssociatesResults() {
        return associateResultRepository.findAll();
    }

    @Override
    public void saveAssociateResults(AssociateResults associateResults) {
        associateResultRepository.save(associateResults);

    }

    @Override
    public void updateAssociateResults(AssociateResults associateResults) {
        associateResultRepository.save(associateResults);
    }


    @Override
    public void deleteAssociateResults(Long resultId) {
        associateResultRepository.deleteById(resultId);

    }
}



