package ru.aolisov.traveling.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.aolisov.traveling.data.entity.Place;
import ru.aolisov.traveling.data.repository.PlaceRepository;

import java.util.List;

/**
 * Created by Alex on 4/11/2016.
 */

@Component
@Scope("session")
public class PlaceService extends AbstractEntityService<Place> {

    @Autowired
    private PlaceRepository repo;

    @Override
    public Place create(Place entity) {
        return super.create(repo, entity);
    }

    @Override
    public Place update(Place entity) {
        return super.update(repo, entity);
    }

    @Override
    public void delete(Place entity) {
        super.delete(repo, entity);
    }

    @Override
    public List<Place> get() {
        return super.get(repo);
    }

    @Override
    public Place get(long id) {
        return super.get(repo, id);
    }

    @Override
    public Place get(String name) {
        return super.get(repo, name);
    }

    @Override
    public boolean exists(long id) {
        return super.exists(repo, id);
    }

    @Override
    public boolean exists(String name) {
        return super.exists(repo, name);
    }
}
