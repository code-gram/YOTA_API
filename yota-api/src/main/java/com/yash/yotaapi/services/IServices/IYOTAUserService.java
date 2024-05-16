package com.yash.yotaapi.services.IServices;

import com.yash.yotaapi.dto.UserProfileDto;
import com.yash.yotaapi.dto.YotaUserDto;

import java.util.List;

/**
 * Project Name - YOTASecurityPOC
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yashr
 * @since - 02-04-2024
 */
public interface IYOTAUserService {

    String createNewUser(YotaUserDto userDto);

    YotaUserDto getUserByEmailAdd(String emailAdd);

    UserProfileDto getUserByEmailAddress(String emailAdd);

    List<YotaUserDto> getAllTrainers();

    List<YotaUserDto> getAllAssociates();

    List<YotaUserDto> getAllPendingUsers();

    Boolean approvePendingUser(String emailAdd);

    Boolean declinePendingUser(String emailAdd);

    List<YotaUserDto> getAllAssociatesByStatus(String status);
}
