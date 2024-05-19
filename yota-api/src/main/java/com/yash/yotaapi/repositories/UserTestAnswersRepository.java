package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.UserTestAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTestAnswersRepository extends JpaRepository<UserTestAnswer, Long> {
}
