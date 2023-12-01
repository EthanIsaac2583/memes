package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

  Optional<Question> findTop1ByQuizIdAndAssessedIs(Long quizId, boolean assessed);
}
