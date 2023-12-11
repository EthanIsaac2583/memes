package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.view.QuestionMetaView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionMetaViewRepository extends JpaRepository<QuestionMetaView, Long> {

  Optional<QuestionMetaView> findTop1ByAssessedIs(boolean assessed);
}
