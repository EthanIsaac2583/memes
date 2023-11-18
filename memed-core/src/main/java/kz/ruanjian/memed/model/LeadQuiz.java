package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "lead_quizzes")
public class LeadQuiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_template_id")
  private QuizTemplate template;

  @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private Set<LeadQuizAnswer> answers;

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

  public Set<LeadQuizAnswer> getAnswers() {
    return answers;
  }

  public void setAnswers(Set<LeadQuizAnswer> answers) {
    this.answers = answers;
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
