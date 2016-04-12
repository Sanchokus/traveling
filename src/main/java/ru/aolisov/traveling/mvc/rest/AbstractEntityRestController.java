package ru.aolisov.traveling.mvc.rest;

import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.aolisov.traveling.data.entity.PersistedEntity;
import ru.aolisov.traveling.data.service.AbstractEntityService;
import ru.aolisov.traveling.mvc.util.MessageObject;
import ru.aolisov.traveling.mvc.util.MessageUtils;

/**
 * Created by Alex on 3/19/2016.
 */
public class AbstractEntityRestController<T extends PersistedEntity> {

    public ResponseEntity<Object> create(AbstractEntityService<T> service, T entity) {
        try {
            service.create(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return MessageUtils.generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> findAll(AbstractEntityService<T> service) {
        try {
            return new ResponseEntity<>(service.get(), HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return MessageUtils.generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> findById(AbstractEntityService<T> service, long id) {
        try {
            Object entity = service.get(id);
            return new ResponseEntity<>(entity, HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return MessageUtils.generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> update(AbstractEntityService<T> service, T entity) {
        try {
            service.update(entity);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return MessageUtils.generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Object> delete(AbstractEntityService<T> service, long id) {
        try {
            service.delete(service.get(id));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (JDBCException e) {
            e.printStackTrace();
            return MessageUtils.generateErrorResponse(e.getSQLException().getLocalizedMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new MessageObject(e.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
