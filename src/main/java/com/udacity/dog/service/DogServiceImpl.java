package com.udacity.dog.service;

import com.udacity.dog.entity.Dog;
import com.udacity.dog.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DogServiceImpl implements DogService{

    @Autowired
    private DogRepository dogRepository;

    @Override
    public List<Dog> dogList() {
        return (List<Dog>) dogRepository.findAll();
    }
}
