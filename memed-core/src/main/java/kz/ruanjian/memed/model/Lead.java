package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "leads")
public class Lead implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(nullable = false)
  private String password;

  private String role;

  @OneToOne
  @JoinColumn(name = "visit_id", updatable = false, nullable = false, unique = true)
  private Visit visit;

  public Lead() {
  }

  private Lead(Builder builder) {
    id = builder.id;
    username = builder.username;
    password = builder.password;
    role = builder.role;
    visit = builder.visit;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  @Override
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonIgnore
  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Visit getVisit() {
    return visit;
  }

  public void setVisit(Visit visit) {
    this.visit = visit;
  }

  @JsonIgnore
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(role));
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @JsonIgnore
  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Lead lead = (Lead) o;
    return Objects.equals(id, lead.id) && Objects.equals(username, lead.username) && Objects.equals(password, lead.password) && Objects.equals(role, lead.role) && Objects.equals(visit, lead.visit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password, role, visit);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Long id;
    private String username;
    private String password;
    private String role;
    private Visit visit;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder password(String password) {
      this.password = password;
      return this;
    }

    public Builder role(String role) {
      this.role = role;
      return this;
    }

    public Builder visit(Visit visit) {
      this.visit = visit;
      return this;
    }

    public Lead build() {
      return new Lead(this);
    }
  }
}
