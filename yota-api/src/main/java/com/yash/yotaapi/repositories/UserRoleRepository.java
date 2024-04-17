package com.yash.yotaapi.repositories;

import com.yash.yotaapi.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    @Query("select ur from UserRole ur where ur.roleId=?1")
    UserRole getUserRoleByRoleId(Long roleId);

    @Query("select ur from UserRole ur where ur.roleTypes=?1")
    UserRole getUserRoleByRoleName(String roleType);

    @Query("select ur from UserRole ur")
    Set<UserRole> getAllUserRoles();
}
