package com.yash.yotaapi.service;

import java.util.List;
import java.util.Set;

import com.yash.yotaapi.domain.AssociateDetailsTest;
import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.exception.TestAlreadyExistsException;
import com.yash.yotaapi.exception.TestNotFoundException;

/**
* TestService is a service layer providing business logic for Test entities.
* It declares methods to perform CRUD operations and search functionalities for Test entities.
*
* @author gaurav.patil
*/
public interface TestService {
	
	/**
	 * Adds a new test to the test_masters table.
	 *
	 * @param test The test to be added to the test master table.
	 * @return The added test if it does not already exists.
	 * @throws TestAlreadyExistsException If the test with the same Name
	 *                                          already exists in the table.
	 */
	Test addTest(Test test);

	/**
	 * This method retrieves a list of all tests stored in the test
	 * master table.
	 *
	 * @return A list containing all tests from the test master table.
	 */
	List<Test> getAllTest();

	/**
	 * Deletes a test from the test_masters table based on its ID. This
	 * method attempts to find a test with the given ID in the
	 * test_masters table. If the test is found, it is deleted. If the
	 * test is not found, a {@link TestNotFoundException} is thrown.
	 *
	 * @param id The ID of the test to be deleted.
	 * @throws TestNotFoundException If a test with the specified ID is
	 *                                     not found in the table.
	 */
	void deleteTestById(Long id);

	/**
	 * Retrieves the details of a test from the test_masters table based
	 * on its ID. This method attempts to find a unit with the given ID in the
	 * test_masters table. If the test is found, its details are
	 * returned. If the test is not found, a
	 * {@link TestNotFoundException} is thrown.
	 *
	 * @param id The ID of the test to be retrieved.
	 * @return The details of the test with the specified ID.
	 * @throws TestNotFoundException If a test with the specified ID is
	 *                                     not found in the table.
	 */
	Test getTestById(Long id);

	/**
	 * Updates the details of a test in the test_masters table based on
	 * its ID. This method attempts to find a unit with the given ID in the
	 * test_masters table. If the test is found, its details are updated
	 * with the provided {@code test}. If the test is not found, a
	 * {@link TestNotFoundException} is thrown.
	 *
	 * @param id         The ID of the test to be updated.
	 * @param test The updated details for the test.
	 * @return The updated test if it exists.
	 * @throws TestNotFoundException If a test with the specified ID is
	 *                                     not found in the table.
	 */
	Test updateTest(Long id, Test test);
	
	/**
	 * Retrieves the details of a test from the test_masters table based
	 * on testName.
	 * 
	 * @param competencyName
	 * @return True if the Test Name exists.
	 */
	Boolean getTestByName(String testName);
	
	
	public Set<AssociateDetailsTest> getAssignedTests(Long id);
}
