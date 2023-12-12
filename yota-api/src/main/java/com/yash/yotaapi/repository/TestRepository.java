package com.yash.yotaapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.Test;
import com.yash.yotaapi.model.request.TestRequest;

/**
 * TestRepository will perform all the CRUD Operations on Test. 
 * In case if any customization is required on CRUD operations, it can be done here. 
 * @author dimpal.gaur
 *
 */
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {

}
