package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {

  Optional<Quiz> findTop1ByStatusAndTemplateId(QuizStatus status, Long templateId);

  default Specification<Quiz> templateIdEquals(@NonNull Long templateId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("template").get("id"), templateId);
  }
}
