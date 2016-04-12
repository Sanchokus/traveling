package ru.aolisov.traveling.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.aolisov.traveling.data.entity.Article;
import ru.aolisov.traveling.data.repository.ArticleRepository;

import java.util.List;

/**
 * Created by Alex on 4/11/2016.
 */

@Component
@Scope("session")
public class ArticleService extends AbstractEntityService<Article> {

    @Autowired
    private ArticleRepository repo;

    @Override
    public Article create(Article entity) {
        return super.create(repo, entity);
    }

    @Override
    public Article update(Article entity) {
        return super.update(repo, entity);
    }

    @Override
    public void delete(Article entity) {
        super.delete(repo, entity);
    }

    @Override
    public List<Article> get() {
        return super.get(repo);
    }

    @Override
    public Article get(long id) {
        return super.get(repo, id);
    }

    @Override
    public Article get(String name) {
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
