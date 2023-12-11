package kz.ruanjian.memed.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "view_question_metas")
@Immutable
public class QuestionMetaView {

  @Id
  private Long id;

  @Column(name = "is_assessed")
  private boolean assessed;

  @Column(name = "rowindex")
  private Long rowIndex;

  @Column(name = "quiz_id")
  private Long quizId;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public boolean isAssessed() {
    return assessed;
  }

  public void setAssessed(boolean assessed) {
    this.assessed = assessed;
  }

  public Long getRowIndex() {
    return rowIndex;
  }

  public void setRowIndex(Long rowIndex) {
    this.rowIndex = rowIndex;
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  @Override
  public String toString() {
    return "QuestionMetaView{" +
      "id=" + id +
      ", assessed=" + assessed +
      ", rowIndex=" + rowIndex +
      ", quizId=" + quizId +
      '}';
  }
}
