package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.UserTrainingTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserTrainingTestRepository extends JpaRepository<UserTrainingTest, Long> {

    boolean existsByTrainingsIdAndTestId(Long trainingId, Long testId);

    boolean existsByTestIdAndUserEmpId(Long testId,  Long empId);

}
