package kz.ruanjian.memed.specifation;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import kz.ruanjian.memed.model.Question;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class QuestionSpecification implements Specification<Question> {

  private Long quizId;
  private UUID visitId;

  public QuestionSpecification() {
  }

  private QuestionSpecification(Builder builder) {
    quizId = builder.quizId;
    visitId = builder.visitId;
  }

  public Long getQuizId() {
    return quizId;
  }

  public void setQuizId(Long quizId) {
    this.quizId = quizId;
  }

  public UUID getVisitId() {
    return visitId;
  }

  public void setVisitId(UUID visitId) {
    this.visitId = visitId;
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    QuestionSpecification that = (QuestionSpecification) o;
    return Objects.equals(quizId, that.quizId) && Objects.equals(visitId, that.visitId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(quizId, visitId);
  }

  @Override
  public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    if (quizId != null) {
      predicates.add(criteriaBuilder.equal(root.get("quiz").get("id"), query));
    }

    if (visitId != null) {
      predicates.add(criteriaBuilder.equal(root.get("visit").get("id"), visitId));
    }

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {

    private Long quizId;
    private UUID visitId;

    public Builder quizId(Long quizId) {
      this.quizId = quizId;
      return this;
    }

    public Builder visitId(UUID visitId) {
      this.visitId = visitId;
      return this;
    }

    public QuestionSpecification build() {
      return new QuestionSpecification(this);
    }
  }
}
