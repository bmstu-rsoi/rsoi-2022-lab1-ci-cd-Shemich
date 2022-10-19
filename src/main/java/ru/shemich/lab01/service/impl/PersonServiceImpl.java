package ru.shemich.lab01.service.impl;

import org.springframework.stereotype.Service;
import ru.shemich.lab01.model.Person;
import ru.shemich.lab01.repository.PersonRepository;
import ru.shemich.lab01.service.PersonService;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Person person) {
        personRepository.save(person);
    }

    @Override
    public Person update(Person person, Person personDetails) {
        if (personDetails.getName() != null) person.setName(personDetails.getName());
        if (personDetails.getAge() != null) person.setAge(personDetails.getAge());
        if (personDetails.getAddress() != null) person.setAddress(personDetails.getAddress());
        if (personDetails.getWork() != null) person.setWork(personDetails.getWork());
        return person;
    }

    @Override
    public void delete(Long id) {
        personRepository.deleteById(id);
    }
}
