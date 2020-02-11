package com.udacity.dog.service;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Dog getById(Long id) {
        Optional<Dog> optionalDog = dogRepository.findById(id);
        Dog dog = optionalDog.orElseThrow(DogNotFoundException::new);
        return dog;
    }

    @Override
    public List<String> getNames() {
        return dogRepository.findName();
    }

    @Override
    public String getBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }
}
