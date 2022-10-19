package ru.shemich.lab01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shemich.lab01.model.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
