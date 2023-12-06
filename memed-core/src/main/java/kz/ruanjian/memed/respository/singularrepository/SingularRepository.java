package kz.ruanjian.memed.respository.singularrepository;

public interface SingularRepository<T, D> {

  Single<T> findSingle();
}
