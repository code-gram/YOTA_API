package com.yash.yotaapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.yash.yotaapi.domain.Client;

@Repository
public interface ClientRespository extends JpaRepository<Client, Long> {

}
