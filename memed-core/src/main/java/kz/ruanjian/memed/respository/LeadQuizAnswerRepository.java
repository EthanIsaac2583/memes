package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.LeadQuizAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadQuizAnswerRepository extends JpaRepository<LeadQuizAnswer, Long> {
}
