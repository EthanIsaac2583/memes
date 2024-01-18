package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.model.Visit;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {

  Optional<Quiz> findTop1ByStatusAndTemplateIdAndVisit(QuizStatus status, Long templateId, Visit visit);

  Long countByTemplateAndVisit(Template template, Visit visit);

  default Specification<Quiz> visitIdEquals(UUID visitId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("visit").get("id"), visitId);
  };
}
