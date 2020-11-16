package com.gasparbarancelli.h2;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PersonService implements ApplicationRunner {

    private static final Logger LOGGER = Logger.getLogger(PersonService.class.getName());

    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var person = new Person("Gaspar Barancelli Junior");

        LOGGER.log(Level.INFO, "Persist");
        repository.save(person);
        LOGGER.log(Level.INFO, person.toString());

        LOGGER.log(Level.INFO, "Find");
        repository.findById(person.getId()).ifPresent(it -> {
            LOGGER.log(Level.INFO, person.toString());
        });

        var person2 = new Person("Rodrigo Barancelli");

        LOGGER.log(Level.INFO, "Persist");
        repository.save(person2);
        LOGGER.log(Level.INFO, person2.toString());

        person2.setName("Rodrigo Dalla Valle Barancelli");
        LOGGER.log(Level.INFO, "Update");
        repository.save(person2);
        LOGGER.log(Level.INFO, person2.toString());

        LOGGER.log(Level.INFO, "FindAll");
        repository.findAll().forEach(it -> LOGGER.log(Level.INFO, it.toString()));

        LOGGER.log(Level.INFO, "Delete");
        repository.delete(person2);
        LOGGER.log(Level.INFO, person2.toString());
    }
}
