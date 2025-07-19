package com.erudio.service;

import com.erudio.controller.PersonController;
import com.erudio.exception.exceptions.NotFoundExecption;
import com.erudio.model.Person;
import com.erudio.respository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public void create(Person person) {
        personRepository.save(person);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Person not found!"));
    }

    public Person update(Person person) {

        Person personToUpdate = personRepository.findById(person.getId())
                .orElseThrow(() -> new NotFoundExecption("Person not found!"));

        personToUpdate.setFirstName(person.getFirstName());
        personToUpdate.setLastName(person.getLastName());
        personToUpdate.setAge(person.getAge());

        return personRepository.save(personToUpdate);
    }

    public void delete(Long id) {

        Person person = personRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Person not found!"));

        personRepository.delete(person);
    }
}
