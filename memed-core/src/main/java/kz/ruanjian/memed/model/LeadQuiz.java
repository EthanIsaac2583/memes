package kz.ruanjian.memed.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "lead_quizzes")
public class LeadQuiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_template_id")
  private QuizTemplate template;

  public LeadQuiz() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public QuizTemplate getTemplate() {
    return template;
  }

  public void setTemplate(QuizTemplate template) {
    this.template = template;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    LeadQuiz leadQuiz = (LeadQuiz) o;
    return Objects.equals(id, leadQuiz.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
