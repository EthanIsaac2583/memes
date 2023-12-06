package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Task;
import kz.ruanjian.memed.respository.singularrepository.SingularRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends SingularRepository<Task, Long> {
}
