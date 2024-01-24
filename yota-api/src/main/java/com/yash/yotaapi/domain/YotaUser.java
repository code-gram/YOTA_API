package com.yash.yotaapi.domain;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "YOTA_USER")
public class YotaUser implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String name;

	private String emailId;

	private String username;

	private String password;

	private boolean enabled;

	@OneToOne
	@JoinColumn(name = "role_id", referencedColumnName = "id")
	private UserRole role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "training_id", referencedColumnName = "id")
	private Training training;

	/*
	 * UserDetails interface methods
	 */

	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return null;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {

		return true;
	}

	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {

		return true;
	}
	
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {

		return true;
	}

	@Override
	@JsonIgnore
	public boolean isEnabled() {

		return true;
	}
}