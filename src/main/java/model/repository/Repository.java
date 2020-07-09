package model.repository;

import java.util.Optional;

public interface Repository<T, ID> {
    Optional<T> findById(ID id);
    Iterable<T> findAll();
    void save(T var);
}
