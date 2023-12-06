package kz.ruanjian.memed.respository.singularrepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface SingularRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {

  Single<T> findSingle();
}
