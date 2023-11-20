package kz.ruanjian.memed.dto;

import java.util.Objects;

public class LeadQuizAnswerGradeDto {

  private Long id;
  private int grade;

  public LeadQuizAnswerGradeDto() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
    LeadQuizAnswerGradeDto that = (LeadQuizAnswerGradeDto) o;
    return grade==that.grade && Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, grade);
  }

  @Override
  public String toString() {
    return "LeadQuizAnswerGradeDto{" +
      "id=" + id +
      ", grade=" + grade +
      '}';
  }
}
