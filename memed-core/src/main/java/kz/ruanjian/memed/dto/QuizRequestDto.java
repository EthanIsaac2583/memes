package kz.ruanjian.memed.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.validation.constraints.NotNull;
import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuizRequestDto implements Specification<Quiz> {

  @NotNull
  private Long templateId;

  public QuizRequestDto() {
  }

  public Long getTemplateId() {
    return templateId;
  }

  public void setTemplateId(Long templateId) {
    this.templateId = templateId;
  }

  @Override
  public Predicate toPredicate(Root<Quiz> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
    List<Predicate> predicates = new ArrayList<>();

    predicates.add(criteriaBuilder.equal(root.get("status"), QuizStatus.IN_PROGRESS));

    predicates.add(criteriaBuilder.equal(root.get("template").get("id"), templateId));

    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
  }

  @Override
  public boolean equals(Object o) {
    if (this==o) return true;
    if (o==null || getClass()!=o.getClass()) return false;
    QuizRequestDto that = (QuizRequestDto) o;
    return Objects.equals(templateId, that.templateId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(templateId);
  }

  @Override
  public String toString() {
    return "RequestActiveQuizDto{" +
      "templateId=" + templateId +
      '}';
  }
}
