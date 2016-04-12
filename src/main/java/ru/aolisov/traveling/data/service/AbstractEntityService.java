package ru.aolisov.traveling.data.service;

import ru.aolisov.traveling.data.entity.PersistedEntity;
import ru.aolisov.traveling.data.repository.EntityRepository;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Alex on 4/11/2016.
 */
public abstract class AbstractEntityService<T extends PersistedEntity> implements EntityService<T> {

    public T create(EntityRepository<T> repo, T entity) {
        if(repo.findOne(entity.getId()) != null) {
            throw new NoSuchElementException(String.format("Found another object with id %d!", entity.getId()));
        }
        return repo.save(entity);
    }

    public T update(EntityRepository<T> repo, T entity) {
        if(repo.findOne(entity.getId()) == null) {
            throw new NoSuchElementException(String.format("Can't find the object with id %d!", entity.getId()));
        }
        return repo.save(entity);
    }

    public void delete(EntityRepository<T> repo, T entity) {
        if(repo.findOne(entity.getId()) == null) {
            throw new NoSuchElementException(String.format("Can't find the object with id %d!", entity.getId()));
        }
        repo.delete(entity);
    }

    public List<T> get(EntityRepository<T> repo) {
        return (List<T>) repo.findAll();
    }

    public T get(EntityRepository<T> repo, long id) {
        if(repo.findOne(id) == null) {
            throw new NoSuchElementException(String.format("Can't find the object with id %d!", id));
        }
        return repo.findOne(id);
    }

    public T get(EntityRepository<T> repo, String name) {
        T entity = repo.findByName(name);
        if(entity == null) {
            throw new NoSuchElementException(String.format("Can't find the object with name %s!", name));
        }
        return entity;
    }

    public boolean exists(EntityRepository<T> repo, long id) {
        return repo.findOne(id) != null;
    }

    public boolean exists(EntityRepository<T> repo, String name) {
        return repo.findByName(name) != null;
    }
}
