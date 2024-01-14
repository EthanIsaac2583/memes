package kz.ruanjian.memed.respository;

import kz.ruanjian.memed.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {

  Optional<Lead> findByUsername(String username);
}
