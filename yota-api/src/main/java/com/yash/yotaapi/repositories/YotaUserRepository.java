package com.yash.yotaapi.repositories;

import com.yash.yotaapi.constants.UserAccountStatusTypes;
import com.yash.yotaapi.entity.YotaUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface YotaUserRepository extends JpaRepository<YotaUser, String> {

    @Query("select yur from YotaUser yur where yur.emailAdd=?1")
    YotaUser getUserByEmail(String email);

    @Query("select yur from YotaUser yur where yur.accountStatus=com.yash.yotaapi.constants.UserAccountStatusTypes.PENDING")
    List<YotaUser> getAllPendingUsers();

    @Query("select yur from YotaUser yur where yur.userRole.roleTypes=?1")
    List<YotaUser> findAllUsersByRole(String roleTypes);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update YotaUser yur set yur.accountStatus=com.yash.yotaapi.constants.UserAccountStatusTypes.APPROVED where yur.emailAdd=?1")
    Integer approvePendingUser(String emailAdd);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update YotaUser yur set yur.accountStatus=com.yash.yotaapi.constants.UserAccountStatusTypes.DECLINED where yur.emailAdd=?1")
    Integer declinePendingUser(String emailAdd);

    @Query("select yur from YotaUser yur where yur.accountStatus=?1")
    List<YotaUser> getAllUserByStatus(UserAccountStatusTypes accountStatus);
}
