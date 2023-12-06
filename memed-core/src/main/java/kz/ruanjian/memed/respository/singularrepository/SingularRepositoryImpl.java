package kz.ruanjian.memed.respository.singularrepository;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

public class SingularRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements SingularRepository<T, ID> {

  public SingularRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
    super(entityInformation, entityManager);
  }

  public SingularRepositoryImpl(Class<T> domainClass, EntityManager entityManager) {
    super(domainClass, entityManager);
  }

  @Override
  public Single<T> findSingle(@Nullable Specification<T> spec, int number) {
    TypedQuery<T> query = this.getQuery(spec, Sort.unsorted());
    query.setFirstResult(Math.max(number - 1, 0));
    query.setMaxResults(1);
    T singleResult = query.getSingleResult();
    Single<T> single = new Single<>();
    single.setContent(singleResult);

    return single;
  }
}
