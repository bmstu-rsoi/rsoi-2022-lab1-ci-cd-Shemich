package ru.shemich.lab01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.shemich.lab01.model.Person;
import ru.shemich.lab01.service.impl.PersonServiceImpl;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/persons", produces = APPLICATION_JSON_VALUE)
public class PersonController {
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public PersonController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<Person>> findAll(Model model){
        List<Person> persons = personServiceImpl.getAll();
        model.addAttribute("persons", persons);
        log.info("Fetching all persons");
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    public ResponseEntity<Person> findPerson (@PathVariable Long personId) {
            Person person = personServiceImpl.getById(personId);
            if (person == null) {
                log.warn("Not found person with id: {}", personId);
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            } else {
                log.info("Found person with id: {}", personId);
                return new ResponseEntity<>(person, HttpStatus.OK);
            }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<String> createPerson(@RequestBody Person person) {
        personServiceImpl.save(person);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location","api/v1/persons/" + person.getId());
        log.info("Create person with id: {}", person.getId());
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{personId}")
    public ResponseEntity<String> deletePerson (@PathVariable Long personId) {
        personServiceImpl.delete(personId);
        log.info("Delete person with id: {}", personId);
        return new ResponseEntity<>("Person with id: " + personId + " deleted", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{personId}")
    public ResponseEntity<Person> updatePerson (@PathVariable Long personId, @RequestBody Person personDetails) {
        Person person = personServiceImpl.getById(personId);
        person = personServiceImpl.update(person, personDetails);
        personServiceImpl.save(person);
        log.info("Updating person with id: {}", personId);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
}

