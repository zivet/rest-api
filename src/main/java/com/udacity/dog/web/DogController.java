package com.udacity.dog.web;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/dog")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogList = dogService.dogList();
        return new ResponseEntity<>(dogList, HttpStatus.OK);
    }
}
