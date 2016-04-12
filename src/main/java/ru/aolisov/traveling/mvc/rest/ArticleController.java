package ru.aolisov.traveling.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aolisov.traveling.data.entity.Article;
import ru.aolisov.traveling.data.service.ArticleService;

/**
 * Created by Alex on 3/19/2016.
 */

@RestController
@RequestMapping("/article")
@Scope("session")
public class ArticleController extends AbstractEntityRestController<Article> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    ArticleService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody Article entity) {
        return super.create(service, entity);
    }

    @RequestMapping(value = {"", "/list"}, method = RequestMethod.GET)
    @ResponseBody
    ResponseEntity<Object> findAll() {
        return super.findAll(service);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> findById(@PathVariable long id) {
        return super.findById(service, id);
    }

    @RequestMapping(value = "", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Object> update(@RequestBody Article entity) {
        return super.update(service, entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable long id) {
        return super.delete(service, id);
    }
}
