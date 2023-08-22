/*
 * package com.yash.yotaapi.domain;
 * 
 * 
 * import java.util.Collection; import java.util.HashSet; import java.util.List;
 * import java.util.Set; import java.util.stream.Collectors;
 * 
 * 
 * import javax.persistence.CascadeType; import javax.persistence.Column; import
 * javax.persistence.Entity; import javax.persistence.FetchType; import
 * javax.persistence.GeneratedValue; import javax.persistence.GenerationType;
 * import javax.persistence.Id; import javax.persistence.JoinColumn; import
 * javax.persistence.JoinColumns; import javax.persistence.JoinTable; import
 * javax.persistence.ManyToMany;
 * 
 * import org.springframework.security.core.GrantedAuthority; import
 * org.springframework.security.core.authority.SimpleGrantedAuthority; import
 * org.springframework.security.core.userdetails.UserDetails;
 * 
 * import lombok.Getter; import lombok.NoArgsConstructor; import lombok.Setter;
 * 
 * @Entity
 * 
 * @NoArgsConstructor
 * 
 * @Getter
 * 
 * @Setter public class User implements UserDetails {
 * 
 * @Id private long id; private String email; private String password;
 * 
 * @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //as one user
 * can have many role and one role can assign to many users //below query we are
 * creating to create new table by joining two tables
 * 
 * @JoinTable(name="User_role", joinColumns = @JoinColumn(name="user",
 * referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role",
 * referencedColumnName = "id"))//here we telling that id is a foreign Key)
 * private Set<Role> roles = new HashSet<>();
 * 
 * @Override public Collection<? extends GrantedAuthority> getAuthorities() {
 * List<SimpleGrantedAuthority> authorities = this.roles.stream().map((role) ->
 * new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
 * return authorities; }
 * 
 * @Override public String getPassword() { return this.password; }
 * 
 * @Override public String getUsername() { return this.email; }
 * 
 * @Override public boolean isAccountNonExpired() { return true; }
 * 
 * @Override public boolean isAccountNonLocked() { return true; }
 * 
 * @Override public boolean isCredentialsNonExpired() { return true; }
 * 
 * @Override public boolean isEnabled() { return true; }
 * 
 * public long getId() { return id; }
 * 
 * public void setId(long id) { this.id = id; }
 * 
 * public String getEmail() { return email; }
 * 
 * public void setEmail(String email) { this.email = email; }
 * 
 * public Set<Role> getRoles() { return roles; }
 * 
 * public void setRoles(Set<Role> roles) { this.roles = roles; }
 * 
 * public void setPassword(String password) { this.password = password; }
 * 
 * 
 * public User(long id, String email, String password, Set<Role> roles) {
 * super(); this.id = id; this.email = email; this.password = password;
 * this.roles = roles; }
 * 
 * public User() { super(); // TODO Auto-generated constructor stub }
 * 
 * 
 * 
 * }
 * 
 */