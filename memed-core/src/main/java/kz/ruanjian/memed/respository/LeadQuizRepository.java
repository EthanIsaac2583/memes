package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadQuizRepository extends JpaRepository<Quiz, Long> {
}
