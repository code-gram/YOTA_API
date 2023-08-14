package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.yash.yotaapi.domain.AssignedTest;

public interface AssociateAssignedTest extends JpaRepository<AssignedTest, Long> {
	@Query(value = "SELECT * from assigned_test  WHERE std_id =:stdId " , nativeQuery = true)
	public List<AssignedTest> getAssignedTestList(@Param("stdId") long stdId);
}
