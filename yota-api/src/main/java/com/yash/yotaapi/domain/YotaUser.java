package com.yash.yotaapi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "YOTA_USER")
public class YotaUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String emailId;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    private boolean enabled;

    private String roles;
}
