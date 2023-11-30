package kz.ruanjian.memed.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.model.Question;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NextQuestionDto implements Specification<Question> {

  @NotNull
  private Long quizId;

  public NextQuestionDto() {
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  @Override
  public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    predicates.add(criteriaBuilder.equal(root.get("isAssessed"), false));
    predicates.add(criteriaBuilder.equal(root.get("quiz").get("id"), quizId));

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    NextQuestionDto that = (NextQuestionDto) o;
    return Objects.equals(quizId, that.quizId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quizId);
  }

  @Override
  public String toString() {
    return "NextQuestionDto{" +
      "quizId=" + quizId +
      '}';
  }
}
