package ru.aolisov.traveling.mvc.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by Alex on 3/19/2016.
 */
public class MessageUtils {
    public static ResponseEntity<Object> generateErrorResponse(String message) {
        return new ResponseEntity<Object>(new MessageObject(message), HttpStatus.BAD_REQUEST);
    }
}
