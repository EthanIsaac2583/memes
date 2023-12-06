package kz.ruanjian.memed.respository.singularrepository;

public interface SingularRepository<T> {

  Single<T> findSingle();
}
