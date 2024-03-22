package com.yash.yotaapi.repository;

import com.yash.yotaapi.domain.YotaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YotaUserRepository extends JpaRepository<YotaUser, Long> {

    public YotaUser findByUsername(String username);

    /**
     * @param roleId
     * @return fetched roleId
     * @author pragati.paliwal
     */
    public List<YotaUser> findByRoleId(Long roleId);

    @Query("select u from YotaUser u where u.name=:name")
    YotaUser getUserByName(String name);
}
