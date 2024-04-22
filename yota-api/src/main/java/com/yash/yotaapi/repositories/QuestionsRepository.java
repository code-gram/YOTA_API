package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionsRepository extends JpaRepository<Questions, Long> {
}

