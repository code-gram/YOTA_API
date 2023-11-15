package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.YotaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface YotaUserRepository extends JpaRepository<YotaUser, Long> {

    public YotaUser findByUsername(String username);
}
