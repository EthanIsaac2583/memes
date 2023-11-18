package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.LeadQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadQuizRepository extends JpaRepository<LeadQuiz, Long> {
}
