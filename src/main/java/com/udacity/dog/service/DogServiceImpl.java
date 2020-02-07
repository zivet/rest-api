package com.udacity.dog.service;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService{

    @Autowired
    private DogRepository dogRepository;

    @Override
    public List<Dog> getAll() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public Optional<Dog> getById(Long id) {
        Optional<Dog> dog = dogRepository.findById(id);
        return dog;
    }

    @Override
    public List<String> getNames() {
        List<String> names = new ArrayList<>();
        dogRepository.findAll().forEach(d -> names.add(d.getName()));
        return names;
    }
}
