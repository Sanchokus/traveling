package ru.aolisov.traveling.data.service;

import ru.aolisov.traveling.data.entity.PersistedEntity;

import java.util.List;

/**
 * Created by Alex on 4/11/2016.
 */
public interface EntityService <T extends PersistedEntity> {
    T create(T entity);
    T update(T entity);
    void delete(T entity);
    List<T> get();
    T get(long id);
    T get(String name);
    boolean exists(long id);
    boolean exists(String name);
}
