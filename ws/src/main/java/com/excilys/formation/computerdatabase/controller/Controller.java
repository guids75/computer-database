package com.excilys.formation.computerdatabase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * Created by excilys on 05/01/17.
 */
public interface Controller {

    default <T> ResponseEntity<T> responseEntity(T object) {
        if (object == null) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<T>(object, HttpStatus.OK);
    }

    default <T> ResponseEntity<List<T>> responseEntityList(List<T> list) {
        if (list.isEmpty()) {
            return new ResponseEntity<List<T>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<T>>(list, HttpStatus.OK);
    }

}
