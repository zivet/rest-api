package com.udacity.dog.web;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.service.DogService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(code = 400, message = "This is a bad request"),
        @ApiResponse(code = 401, message = "You are not authorized"),
        @ApiResponse(code = 404, message = "Resource not fount"),
        @ApiResponse(code = 500, message = "Server is down")
})
public class DogController {

    @Autowired
    private DogService dogService;

    @GetMapping(value = "/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogList = dogService.getAll();
        return new ResponseEntity<>(dogList, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER')")
    @GetMapping(value = "/dog")
    public ResponseEntity<Dog> getDogById(@RequestParam(value = "id", required = true) Long id) {
        Dog dog = dogService.getById(id);
        return new ResponseEntity<>(dog, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('MANAGER') OR hasRole('USER')")
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
