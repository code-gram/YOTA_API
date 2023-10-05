package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.CreateClientQuestion;
@Repository
public interface CreateClientQuestionRepository extends JpaRepository<CreateClientQuestion, Long> {

}
