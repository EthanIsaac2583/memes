package kz.ruanjian.memed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "sessions")
public class Session {

  @Id
  @GeneratedValue
  private Long id;

  private String token;

  private boolean revoked;

  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lead_id")
  private Lead lead;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public boolean isRevoked() {
    return revoked;
  }

  public void setRevoked(boolean revoked) {
    this.revoked = revoked;
  }

  public boolean isExpired() {
    return expired;
  }

  public void setExpired(boolean expired) {
    this.expired = expired;
  }

  public Lead getLead() {
    return lead;
  }

  public void setLead(Lead lead) {
    this.lead = lead;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Session session = (Session) o;
    return revoked==session.revoked && expired==session.expired && Objects.equals(id, session.id) && Objects.equals(token, session.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, token, revoked, expired);
  }
}
