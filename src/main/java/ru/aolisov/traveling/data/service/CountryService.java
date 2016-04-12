package ru.aolisov.traveling.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.aolisov.traveling.data.entity.Country;
import ru.aolisov.traveling.data.repository.CountryRepository;

import java.util.List;

/**
 * Created by Alex on 4/11/2016.
 */

@Component
@Scope("session")
public class CountryService extends AbstractEntityService<Country> {

    @Autowired
    private CountryRepository repo;

    @Override
    public Country create(Country entity) {
        return super.create(repo, entity);
    }

    @Override
    public Country update(Country entity) {
        return super.update(repo, entity);
    }

    @Override
    public void delete(Country entity) {
        super.delete(repo, entity);
    }

    @Override
    public List<Country> get() {
        return super.get(repo);
    }

    @Override
    public Country get(long id) {
        return super.get(repo, id);
    }

    @Override
    public Country get(String name) {
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
