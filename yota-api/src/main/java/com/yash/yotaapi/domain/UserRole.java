package com.yash.yotaapi.domain;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Entity
@Table(name = "USER_ROLE")
public class UserRole {
    @Id
    private long id;
    private String description;
}
