package com.yash.yotaapi.service;

import java.util.List;

import com.yash.yotaapi.domain.AssociateResults;

/**
 * TestService will perform the business logic related to test
 * @author bhavana.thakare
 */
public interface AssociateResultservice {

    /**
     * This service is to Result of add associate.
     * @param resultId
     * @return
     */
    AssociateResults getAssociateResultsBYId(Long resultId);

    /**
     *  It gives list of all the Result of associates.
     * @return
     */
    List<AssociateResults> getAllAssociatesResults();

    /**
     * Save all Associate Results
     * @param associateResults
     */
    void saveAssociateResults(AssociateResults associateResults);

    /**
     * It update all associate results
     * @param associateResults
     */
   void updateAssociateResults(AssociateResults associateResults);

    /**
     *It delete associate results
     * @param resultId
     */
   void deleteAssociateResults(Long resultId);


}



