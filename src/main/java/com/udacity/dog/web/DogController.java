package com.udacity.dog.web;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogList = dogService.getAll();
        return new ResponseEntity<>(dogList, HttpStatus.OK);
    }

    @GetMapping(value = "/dog")
    public ResponseEntity<Dog> getDogById(@RequestParam(value = "id", required = true) Long id) {
        Optional<Dog> dog = dogService.getById(id);
        if(dog.isPresent()) {
            return new ResponseEntity<>(dog.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/dogs/name")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> dogNames = dogService.getNames();
        return new ResponseEntity<>(dogNames, HttpStatus.OK);
    }

    @GetMapping(value = "/dogs/{id}/breed")
    public ResponseEntity<String> getDogBreedById(@PathVariable("id") Long id) {
        String breed = dogService.getBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }
}
