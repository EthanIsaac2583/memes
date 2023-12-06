package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.respository.singularrepository.SingularRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends SingularRepository<Question, Long>, JpaSpecificationExecutor<Question> {

  Optional<Question> findTop1ByQuizIdAndAssessedIs(Long quizId, boolean assessed);

  default Specification<Question> quizIdEquals(@NonNull Long quizId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("quiz").get("id"), quizId);
  }
}
