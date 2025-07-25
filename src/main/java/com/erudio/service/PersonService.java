package com.erudio.service;

import com.erudio.exception.exceptions.NotFoundExecption;
import com.erudio.model.Person;
import com.erudio.respository.PersonRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<Person> getAllPaginate(Pageable pageable) {
        return personRepository.findAll(pageable);
    }

    public Page<Person> findByFirstName(String firstName, Pageable pageable) {
        return personRepository.findByfirstNameContainingIgnoreCase(firstName, pageable);
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

    @Transactional
    public void disable(Long id) {

        personRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Person not found!"));

        personRepository.disbalePerson(id);
    }

    @Transactional
    public void enable(Long id) {
        var person = personRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Person not found!"));
        person.setStatus(true);
    }
}
