package kz.ruanjian.memed.respository.singularrepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class SingularRepositoryImpl<T> implements SingularRepository<T> {

  @PersistenceContext
  private final EntityManager entityManager;

  public SingularRepositoryImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public Single<T> findSingle() {
    return null;
  }
}
