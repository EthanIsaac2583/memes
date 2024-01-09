package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "visits")
public class Visit {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @JsonIgnore
  @Column(name = "user_agent", updatable = false)
  private String userAgent;

  @JsonIgnore
  @Column(name = "created_at", updatable = false)
  private ZonedDateTime created_at;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public ZonedDateTime getCreated_at() {
    return created_at;
  }

  public void setCreated_at(ZonedDateTime created_at) {
    this.created_at = created_at;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Visit visit = (Visit) o;
    return Objects.equals(id, visit.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
