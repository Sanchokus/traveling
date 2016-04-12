package ru.aolisov.traveling.mvc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.aolisov.traveling.data.entity.Place;
import ru.aolisov.traveling.data.service.PlaceService;

/**
 * Created by Alex on 3/19/2016.
 */
@RestController
@RequestMapping("/place")
@Scope("session")
public class PlaceController extends AbstractEntityRestController<Place> {

    @Autowired
    @SuppressWarnings("SpringJavaAutowiringInspection")
    PlaceService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Object> create(@RequestBody Place entity) {
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
    public ResponseEntity<Object> update(@RequestBody Place entity) {
        return super.update(service, entity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Object> delete(@PathVariable long id) {
        return super.delete(service, id);
    }
}
