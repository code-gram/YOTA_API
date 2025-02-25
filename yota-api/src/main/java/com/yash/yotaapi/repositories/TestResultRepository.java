package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TestResultRepository extends JpaRepository<TestResult, Long> {



    @Query(value = "select  * from test_results where test_id= :testId AND user_id= :userId AND result= :result ", nativeQuery = true)
   Optional<TestResult> findByTestIdAndUserIdAndResult(Long testId, Long userId, Long result);
    //AND result= :result
    //, Long result
    //List<TestResult> findByUserEmpId(Long empId);
    
//    @Query(value = "select  * from test_results where test_id= :testId AND user_id= :userId ", nativeQuery = true)
//    TestResult getByTestIdAndUserId(Long testId, Long userId);
//    
    TestResult findByUserEmpIdAndTestId(Long empId, Long testId);
}
