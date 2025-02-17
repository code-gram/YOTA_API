package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.TPR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TPRRepository extends JpaRepository<TPR, Long> {

    @Query(value = "select  * from tpr where training_id = :trainingId AND emp_id = :empId", nativeQuery = true)
    Optional<TPR> findByTrainingIdAndUserId(Long trainingId, Long empId);
}
