package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.QuizTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTemplateRepository extends JpaRepository<QuizTemplate, Long> {
}
