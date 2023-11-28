package com.yash.yotaapi.service;

import java.util.Optional;

import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestException;
import com.yash.yotaapi.exception.TestNotFoundException;

   /**
    * TestService will perform the business logic related to test
    * @author dimpal.gaur
    */
    public interface TestService {
  
	/**
	 * saveOrUpdate method will create Test if Id is not available
     * saveOrUpdate method will update the Test if Id is available
     * @param test test is an object that is suppose to be save or update
     * @return it will return the test object in case of save or update
	 * @throws TestException will be thrown in case of test will not be saved or updated
	 */
    	
	public Test saveOrUpdate(Test test) throws TestException;

    /**
     * findByName method will fetch test based on its id from DB
	 * @param id
	 * @return test according to test id
     * @throws TestNotFoundException
     */
	public Optional<Test> findTestById(long id) throws TestNotFoundException;
	
	/**
	 * findAllTest method will fetch all Test from DB
	 * @return List of Test
	 */
	
	public Iterable<Test> findAllTest();
	
	/**
	 * deleteTestByName method will delete the test that is mentioned
	 * @param id
	 * @return if deleted, otherwise false, in case test is not available then it should throw appropriate exception.
	 * @throws TestNotFoundException
	 */
	
	public void deleteTestById(long id) throws TestNotFoundException;
	
	/**
      * this updateTest method will update the test detail of the existing test
	 * @param test to be updated
	 * @return Updated Test
	 * @throws TestException
	 */
	
	public Test updateTest(Test test) throws TestException;

}
