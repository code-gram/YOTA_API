package com.yash.yotaapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.entity.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Long>  {
	Test findByTestName(String test);

}
