package kz.ruanjian.memed.respository.singularrepository;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

public class SingularRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SingularRepository<T, ID> {

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
    System.out.println("-------> it works!");
    return new Single<>();
  }
}
