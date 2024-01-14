package kz.ruanjian.memed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions")
public class Session {

  @Id
  @GeneratedValue
  private Long id;

  private String token;

  private boolean revoked;

  private boolean expired;
}
