package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yash.yotaapi.domain.CreateClient;

@Repository
public interface CreateClientRespository extends JpaRepository<CreateClient, Long> {

}
