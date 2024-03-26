package com.yash.yotaapi.repository;
 
import com.yash.yotaapi.domain.YotaUser;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
@Repository
public interface YotaUserRepository extends JpaRepository<YotaUser, Long> {
 
    public YotaUser findByUsername(String username);
    
    /**
     * @author pragati.paliwal
     * @param roleId
     * @return fetched roleId 
     */
    public List<YotaUser> findByRoleId(Long roleId);
    
    @Query("select u from YotaUser u where u.name=:name")
    YotaUser getUserByName(String name);
}