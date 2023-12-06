package kz.ruanjian.memed.respository.singularrepository;

import jakarta.annotation.Nullable;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    Pageable pageable = PageRequest.of(number, 1);
    TypedQuery<T> query = this.getQuery(spec, pageable);
    Page<T> page = this.readPage(query, this.getDomainClass(), pageable, spec);

    return mapToSingle(page);
  }

  private Single<T> mapToSingle(Page<T> page) {
    Single<T> single = new Single<>();

    single.setHasNext(page.hasNext());
    single.setHasPrevious(page.hasPrevious());
    single.setSize(page.getTotalPages());
    if (!page.getContent().isEmpty()) {
      single.setContent(page.getContent().get(0));
    }

    return single;
  }
}
