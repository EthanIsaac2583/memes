package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "quizzes")
public class Quiz {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "visit_id")
  private Visit visit;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "template_id")
  private Template template;

  @JsonIgnore
  @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  private Set<Question> questions;

  @Enumerated(EnumType.STRING)
  private QuizStatus status;

  private int grade;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Visit getVisit() {
    return visit;
  }

  public void setVisit(Visit visit) {
    this.visit = visit;
  }

  public Template getTemplate() {
    return template;
  }

  public void setTemplate(Template template) {
    this.template = template;
  }

  public Set<Question> getQuestions() {
    return questions;
  }

  public void setQuestions(Set<Question> questions) {
    this.questions = questions;
  }

  public QuizStatus getStatus() {
    return status;
  }

  public void setStatus(QuizStatus status) {
    this.status = status;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Quiz quiz = (Quiz) o;
    return grade==quiz.grade && Objects.equals(id, quiz.id) && Objects.equals(visit, quiz.visit) && Objects.equals(template, quiz.template) && Objects.equals(questions, quiz.questions) && status==quiz.status;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, visit, template, questions, status, grade);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Long id;
    private Visit visit;
    private Template template;
    private Set<Question> questions;
    private QuizStatus status;
    private int grade;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder visit(Visit visit) {
      this.visit = visit;
      return this;
    }

    public Builder template(Template template) {
      this.template = template;
      return this;
    }

    public Builder questions(Set<Question> questions) {
      this.questions = questions;
      return this;
    }

    public Builder status(QuizStatus status) {
      this.status = status;
      return this;
    }

    public Builder grade(int grade) {
      this.grade = grade;
      return this;
    }

    public Quiz build() {
      return new Quiz(this);
    }
  }
}
