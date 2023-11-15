package kz.ruanjian.memed.model;

import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.ruanjian.memed.pojo.coverter.QuestionConverter;
import kz.ruanjian.memed.pojo.quiestion.Question;

@Entity
@Table(name = "tasks")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private String description;

  @Convert(converter = QuestionConverter.class)
  private Question question;
}
