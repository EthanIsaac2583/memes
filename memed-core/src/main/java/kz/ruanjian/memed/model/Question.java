package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.coverter.AnswerConverter;

import java.util.Objects;

@Entity
@Table(name = "questions")
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(updatable = false)
  private Integer number;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "quiz_id", updatable = false)
  private Quiz quiz;

  @ManyToOne
  @JoinColumn(name = "task_id", updatable = false)
  private Task task;

  @Column(name = "is_assessed")
  private boolean assessed;

  private int grade;

  @Convert(converter = AnswerConverter.class)
  private Answer answer;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "visit_id", updatable = false)
  private Visit visit;

  public Question() {
  }

  private Question(Builder builder) {
    id = builder.id;
    number = builder.number;
    quiz = builder.quiz;
    task = builder.task;
    assessed = builder.assessed;
    grade = builder.grade;
    answer = builder.answer;
    visit = builder.visit;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public boolean isAssessed() {
    return assessed;
  }

  public void setAssessed(boolean assessed) {
    this.assessed = assessed;
  }

  public int getGrade() {
    return grade;
  }

  public void setGrade(int grade) {
    this.grade = grade;
  }

  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
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
    Question question = (Question) o;
    return assessed==question.assessed && grade==question.grade && Objects.equals(id, question.id) && Objects.equals(task, question.task) && Objects.equals(answer, question.answer) && Objects.equals(visit, question.visit);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, task, assessed, grade, answer, visit);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private Long id;
    private Integer number;
    private Quiz quiz;
    private Task task;
    private boolean assessed;
    private int grade;
    private Answer answer;
    private Visit visit;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder number(Integer number) {
      this.number = number;
      return this;
    }

    public Builder quiz(Quiz quiz) {
      this.quiz = quiz;
      return this;
    }

    public Builder task(Task task) {
      this.task = task;
      return this;
    }

    public Builder assessed(boolean assessed) {
      this.assessed = assessed;
      return this;
    }

    public Builder grade(int grade) {
      this.grade = grade;
      return this;
    }

    public Builder answer(Answer answer) {
      this.answer = answer;
      return this;
    }

    public Builder visit(Visit visit) {
      this.visit = visit;
      return this;
    }

    public Question build() {
      return new Question(this);
    }
  }
}
