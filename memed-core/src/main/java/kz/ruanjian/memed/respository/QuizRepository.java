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

  Optional<Quiz> findByIdAndVisitId(Long id, UUID visitId);

  Optional<Quiz> findTop1ByStatusAndTemplateIdAndVisitId(QuizStatus status, Long templateId, UUID visitId);

  Long countByTemplateAndVisit(Template template, Visit visit);

  default Specification<Quiz> visitIdEquals(UUID visitId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("visit").get("id"), visitId);
  };
}
