package kz.ruanjian.memed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import kz.ruanjian.memed.pojo.answer.Answer;
import kz.ruanjian.memed.pojo.blank.Blank;
import kz.ruanjian.memed.pojo.coverter.AnswerConverter;
import kz.ruanjian.memed.pojo.coverter.BlankConverter;
import kz.ruanjian.memed.pojo.coverter.BodyConverter;
import kz.ruanjian.memed.pojo.body.Body;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.util.Objects;

@Entity
@Table(name = "tasks")
@SQLDelete(sql = "UPDATE tasks SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @Convert(converter = BodyConverter.class)
  private Body body;

  @Convert(converter = BlankConverter.class)
  private Blank blank;

  @Convert(converter = AnswerConverter.class)
  private Answer answer;

  @Column(name = "is_deleted")
  private boolean isDeleted;

  public Task() {
  }

  private Task(Builder builder) {
    id = builder.id;
    name = builder.name;
    body = builder.body;
    blank = builder.blank;
    answer = builder.answer;
    isDeleted = builder.isDeleted;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Body getBody() {
    return body;
  }

  public void setBody(Body body) {
    this.body = body;
  }

  public Blank getBlank() {
    return blank;
  }

  public void setBlank(Blank blank) {
    this.blank = blank;
  }

  public Answer getAnswer() {
    return answer;
  }

  public void setAnswer(Answer answer) {
    this.answer = answer;
  }

  @JsonIgnore
  public boolean isDeleted() {
    return isDeleted;
  }

  public void setDeleted(boolean deleted) {
    isDeleted = deleted;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    Task task = (Task) o;
    return isDeleted==task.isDeleted && Objects.equals(id, task.id) && Objects.equals(name, task.name) && Objects.equals(body, task.body) && Objects.equals(blank, task.blank) && Objects.equals(answer, task.answer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, body, blank, answer, isDeleted);
  }

  @Override
  public String toString() {
    return "Task{" +
      "id=" + id +
      ", name='" + name + '\'' +
      ", body=" + body +
      ", blank=" + blank +
      ", answer=" + answer +
      ", isDeleted=" + isDeleted +
      '}';
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Long id;
    private String name;
    private Body body;
    private Blank blank;
    private Answer answer;
    private boolean isDeleted;

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder body(Body body) {
      this.body = body;
      return this;
    }

    public Builder blank(Blank blank) {
      this.blank = blank;
      return this;
    }

    public Builder answer(Answer answer) {
      this.answer = answer;
      return this;
    }

    public Builder isDeleted(boolean isDeleted) {
      this.isDeleted = isDeleted;
      return this;
    }

    public Task build() {
      return new Task(this);
    }
  }
}
