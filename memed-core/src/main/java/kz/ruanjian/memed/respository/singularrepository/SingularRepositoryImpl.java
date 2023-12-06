package kz.ruanjian.memed.respository.singularrepository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class SingularRepositoryImpl<T, D> extends SimpleJpaRepository<T, D> implements SingularRepository<T, D> {

  private final EntityManager entityManager;

  public SingularRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
    this.entityManager = entityManager;
  }

  public SingularRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
    super(domainClass, entityManager);
    this.entityManager = entityManager;
  }

  @Override
  public Single<T> findSingle() {
    return null;
  }
}
