package kz.ruanjian.memed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "leads")
public class Lead {

  @Id
  @GeneratedValue
  private Long id;

  private String username;

  private String password;

  @OneToOne
  @JoinColumn(name = "visit_id")
  private Visit visit;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Visit getVisit() {
    return visit;
  }

  public void setVisit(Visit visit) {
    this.visit = visit;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Lead lead = (Lead) o;
    return Objects.equals(id, lead.id) && Objects.equals(username, lead.username) && Objects.equals(password, lead.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, password);
  }
}
