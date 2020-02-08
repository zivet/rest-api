package com.udacity.dog.service;

import com.udacity.dog.entity.Dog;

import java.util.List;

public interface DogService {

    List<Dog> getAll();

    Dog getById(Long id);

    List<String> getNames();

    String getBreedById(Long id);
}
