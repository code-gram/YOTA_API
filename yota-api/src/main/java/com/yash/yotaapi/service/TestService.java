package com.yash.yotaapi.service;

import com.yash.yotaapi.domain.Test;

/**
 * testService will be performing business logic related to Test Bank.
 * @author jaie.arkadi
 *
 */
public interface TestService {
	
	/**
	 * saveOrUpdate method will save the tests.
	 * @param test to be saved
	 * @return Saved test, it should hold the id of the new test saved in DB
	 *
	 */

	public Test saveOrUpdate(Test test);

	/**

	 * findById method will fetch test based on its id from DB
	 * @param testId
	 * @return tests according to tests id


	 */
	public Test findTestById(Long testId);
	
	/**
	 * findAlltest method will fetch all tests from DB
	 * @return List of tests
	 */
	public Iterable<Test> findAllTest();
	
	/**

	 * deletetestById method will delete the test that is mentioned
	 * @param testId
	 * @return if deleted, otherwise false, in case test is not available then it should throw appropriate exception.

	 */
	
	public void deleteTestById(Long testId);
	
	/**
	 * this updatetest method will update the test detail of the existing test
	 * @param test to be updated
	 * @return Updated test
	 */
	public Test updateTest(Test test);
}
