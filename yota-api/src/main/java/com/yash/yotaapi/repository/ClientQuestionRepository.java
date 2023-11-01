package com.yash.yotaapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yash.yotaapi.domain.ClientQuestion;

@Repository
public interface ClientQuestionRepository extends JpaRepository<ClientQuestion, Long> {

	public List<ClientQuestion> findByClientId(String clientId);
}
