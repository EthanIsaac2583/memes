package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Template;
import kz.ruanjian.memed.respository.singularrepository.SingularRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateRepository extends SingularRepository<Template, Long> {
}
