package com.yash.yotaapi.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yash.yotaapi.constants.UserAccountStatusTypes;
import lombok.Data;

/**
 * Project Name - YOTASecurityAPI
 * <p>
 * IDE Used - IntelliJ IDEA
 *
 * @author - yash.raj
 * @since - 02-04-2024
 */
@Data
public class YotaUserDto {

    private String fullName;

    private String emailAdd;

    private String password;

    private String confirmPassword;

    private UserAccountStatusTypes accountStatus;

    private UserRoleDto userRole;

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getConfirmPassword() {
        return confirmPassword;
    }

    @JsonProperty
    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
