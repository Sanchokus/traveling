package ru.aolisov.traveling.mvc.util;

import java.io.Serializable;

/**
 * Created by Alex on 3/19/2016.
 */

public class MessageObject implements Serializable {
    private String message = "";

    public MessageObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
