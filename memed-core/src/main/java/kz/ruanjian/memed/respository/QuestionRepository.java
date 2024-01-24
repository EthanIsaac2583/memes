package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Question;
import kz.ruanjian.memed.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

  @Query("SELECT q.number FROM Question q WHERE q.assessed = false AND q.quiz.id = :quizId ORDER BY q.number LIMIT 1")
  Optional<Long> findFirstAssessableQuestionNumber(@Param("quizId") Long quizId);

  Optional<Question> findByIdAndQuizIdAndVisit(Long id, Long quizId, Visit visit);
}
