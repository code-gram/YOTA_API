package com.yash.yotaapi.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yash.yotaapi.domain.EmployeeResult;
import com.yash.yotaapi.exception.MarksNotFound;
import com.yash.yotaapi.repository.EmployeeResultRepository;
import com.yash.yotaapi.service.EmployeeResultService;

@Service
public class EmployeeResultServiceImpl implements EmployeeResultService {

	@Autowired
	private EmployeeResultRepository employeeResultRepository;

	/**
	 * This method is for get ParentTechnology from DB through repository layer on
	 * basis of name only.
	 */
	@Override
	public List<EmployeeResult> getMarksByOrder(String marksOrder) {
		List<EmployeeResult> employeeResult;
		if (marksOrder.equals("asc")) {
			System.out.println("asc");
			employeeResult = employeeResultRepository.findByOrderByMarksAsc();
		} else {
			System.out.println("desc");
			employeeResult = employeeResultRepository.findByOrderByMarksDesc();
		}
		if (employeeResult == null) {
			throw new MarksNotFound("Result does not exist");
		}
		return employeeResult;
	}

	@Override
	public List<EmployeeResult> save(EmployeeResult employeeResult) {
		try {
			return (List<EmployeeResult>) employeeResultRepository.save(employeeResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
