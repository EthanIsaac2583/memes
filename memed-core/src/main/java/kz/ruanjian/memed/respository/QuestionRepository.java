package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Question;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

  Optional<Question> findTop1ByQuizIdAndAssessedIs(Long quizId, boolean assessed);

  @Query("SELECT q.number FROM Question q WHERE q.assessed = false AND q.quiz.id = :quizId ORDER BY q.number LIMIT 1")
  Optional<Long> findFirstAssessableQuestionNumber(@Param("quizId") Long quizId);

  default Specification<Question> quizIdEquals(@NonNull Long quizId) {
    return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("quiz").get("id"), quizId);
  }
}
