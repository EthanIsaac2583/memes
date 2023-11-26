package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTemplateRepository extends JpaRepository<Template, Long> {
}
