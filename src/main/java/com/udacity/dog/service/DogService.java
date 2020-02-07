package com.udacity.dog.service;

import com.udacity.dog.entity.Dog;

import java.util.List;
import java.util.Optional;

public interface DogService {

    List<Dog> getAll();

    Optional<Dog> getById(Long id);

    List<String> getNames();
}
