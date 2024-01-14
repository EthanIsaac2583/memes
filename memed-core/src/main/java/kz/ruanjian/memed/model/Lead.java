package kz.ruanjian.memed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "leads")
public class Lead {

  @Id
  @GeneratedValue
  private Long id;

  private
}
