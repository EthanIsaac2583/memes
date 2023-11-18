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
@Table(name = "lead_quiz_answers")
public class LeadQuizAnswer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "lead_quiz_id", updatable = false)
  private LeadQuiz quiz;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "task_id", updatable = false)
  private Task task;

  @Column(name = "is_remote_assessable", updatable = false)
  private boolean isRemoteAssessable;

  @Column(name = "is_assessed")
  private boolean isAssessed;

  private int grade;

  @Convert(converter = AnswerConverter.class)
  private Answer answer;

  public LeadQuizAnswer() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LeadQuiz getQuiz() {
    return quiz;
  }

  public void setQuiz(LeadQuiz quiz) {
    this.quiz = quiz;
  }

  public Task getTask() {
    return task;
  }

  public void setTask(Task task) {
    this.task = task;
  }

  public boolean isRemoteAssessable() {
    return isRemoteAssessable;
  }

  public void setRemoteAssessable(boolean remoteAssessable) {
    isRemoteAssessable = remoteAssessable;
  }

  public boolean isAssessed() {
    return isAssessed;
  }

  public void setAssessed(boolean assessed) {
    isAssessed = assessed;
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

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    LeadQuizAnswer that = (LeadQuizAnswer) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
