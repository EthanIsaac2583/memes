package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Quiz;
import kz.ruanjian.memed.model.QuizStatus;
import kz.ruanjian.memed.respository.singularrepository.SingularRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends SingularRepository<Quiz, Long>, JpaSpecificationExecutor<Quiz> {

  Optional<Quiz> findTop1ByStatusAndTemplateId(QuizStatus status, Long templateId);
}
