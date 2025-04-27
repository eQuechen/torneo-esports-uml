package es.empresa.torneo.persistencia.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, ID, R> {
    Optional<T> findById(ID primaryKey);
    R insertOne(T object);
    R updateOne(T object);
    R deleteById(ID primaryKey);
    R deleteByObject(T object);
    List<T> findAll();
}