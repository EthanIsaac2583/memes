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

  public Question() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Question question = (Question) o;
    return assessed==question.assessed && grade==question.grade && Objects.equals(id, question.id) && Objects.equals(quiz, question.quiz) && Objects.equals(task, question.task) && Objects.equals(answer, question.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, quiz, task, assessed, grade, answer);
  }

  @Override
  public String toString() {
    return "Question{" +
      "id=" + id +
      ", quiz=" + quiz +
      ", task=" + task +
      ", assessed=" + assessed +
      ", grade=" + grade +
      ", answer=" + answer +
      '}';
  }
}
