package ru.shemich.lab01.service;

import ru.shemich.lab01.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getAll();
    Person getById(Long id);
    void save(Person person);
    Person update(Person person, Person personDetails);
    void delete(Long id);

}
