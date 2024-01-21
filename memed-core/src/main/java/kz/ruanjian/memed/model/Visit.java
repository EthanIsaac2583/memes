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
  @Column(name = "created_at", updatable = false)
  private ZonedDateTime createdAt;

  public Visit() {
  }

  private Visit(Builder builder) {
    id = builder.id;
    createdAt = builder.createdAt;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public ZonedDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(ZonedDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Visit visit = (Visit) o;
    return Objects.equals(id, visit.id) && Objects.equals(createdAt, visit.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, createdAt);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private UUID id;
    private ZonedDateTime createdAt;

    public Builder id(UUID id) {
      this.id = id;
      return this;
    }

    public Builder createdAt(ZonedDateTime createdAt) {
      this.createdAt = createdAt;
      return this;
    }

    public Visit build() {
      return new Visit(this);
    }
  }
}
