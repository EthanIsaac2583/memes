package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {
}
