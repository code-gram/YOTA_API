package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Trainings;
import com.yash.yotaapi.entity.YotaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface TrainingRepository extends JpaRepository<Trainings, Long> {

    @Modifying
    @Transactional
    @Query(value = "insert into trainings_assign (trainings_id, assign_email_add) values (:trainingId, :emailId)", nativeQuery = true)
    Integer addAssignTraining(@Param("trainingId") Integer trainingId,
                              @Param("emailId") String emailId);

    @Query(value = "select count(trainings_id) from trainings_assign where trainings_id= :trainingId", nativeQuery = true)
    Integer registeredCount(@Param("trainingId") Integer trainingId);

    @Modifying
    @Query(value = "update Trainings t set t.registered_in_training= :registeredCount where t.id= :id", nativeQuery = true)
    Integer updateRegisteredCount(@Param("id") Integer id,
                                  @Param("registeredCount") Integer registeredCount);

    @Query(value = "select * from trainings_assign where trainings_id= :trainingId", nativeQuery = true)
    List<Object[]> assignedAssociated(@Param("trainingId") Integer trainingId);


    @Query(value = "select trainings_id from trainings_assign  trainings_id where trainings_id.assign_email_add=?1", nativeQuery = true)
    List<Long> getTrainingIdByEmailId(@Param("assign_email_add") String assign_email_add);

    @Query(value = "select * from trainings where id= :id", nativeQuery = true)
    Trainings getTrainingById(@Param("id") Long trainingId);
    

    @Query(value = "select tr.id,tr.training_name,u.email_add,u.full_name,u.emp_id, round(sum(r.`result`)*100/sum(tst.max_marks),1) as avginpercentage"
    		+ " from trainings tr,training_test_assign trt,tests tst,test_results r,yota_user u"
    		+ "  where tr.id=:trainingId"
    		+ " and tr.id=trt.training_id"
    		+ " and tst.test_id=trt.test_id"
    		+ "  and r.test_id=trt.test_id"
    		+ " and u.emp_id=r.user_id "
    		+ "  group by tr.id,tr.training_name,u.email_add,full_name,u.emp_id"
    		+ "  order by u.full_name", nativeQuery = true)
    List<Object[]> getTprReport(@Param("trainingId") Integer trainingId);
    
    @Query(value = ""
    		+ "select tst.test_title,u.full_name,u.emp_id,r.`result`,"
    		+ "round((r.`result`)*100/(tst.max_marks),1) as avginpercentage"
    		+ " from trainings tr,training_test_assign trt,tests tst,test_results r,yota_user u"
    		+ "  where tr.id=:trainingId"
    		+ " and tr.id=trt.training_id"
    		+ " and tst.test_id=trt.test_id"
    		+ "  and r.test_id=trt.test_id"
    		+ " and u.emp_id=r.user_id "
    		+ " and u.emp_id=:empId"
    		+ "  order by tst.test_title", nativeQuery = true)
    List<Object[]> getEmployeeWiseTestReport(@Param("trainingId") Integer trainingId,@Param("empId") Integer empId);


}
