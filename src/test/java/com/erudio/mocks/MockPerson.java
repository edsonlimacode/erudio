package com.erudio.mocks;

import com.erudio.data.tdo.PersonDto;
import com.erudio.model.Person;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {


    public Person mockEntity() {
        return mockEntity(0);
    }
    
    public PersonDto mockDTO() {
        return mockDTO(0);
    }
    
    public List<Person> mockEntityList() {
        List<Person> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockEntity(i));
        }
        return persons;
    }

    public List<PersonDto> mockDTOList() {
        List<PersonDto> persons = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            persons.add(mockDTO(i));
        }
        return persons;
    }
    
    public Person mockEntity(Integer number) {
        Person person = new Person();
        person.setFirstName("First Name Test" + number);
        person.setLastName("Last Name Test" + number);
        person.setId(number.longValue());
        person.setAge(20);
        person.setGenre(((number % 2)==0) ? "Male" : "Female");
        return person;
    }

    public PersonDto mockDTO(Integer number) {
        PersonDto personDto = new PersonDto();
        personDto.setId(number.longValue());
        personDto.setFirstName("First Name Test" + number);
        personDto.setLastName("Last Name Test" + number);
        personDto.setGenre(((number % 2)==0) ? "Male" : "Female");
        personDto.setAge(20);
        return personDto;
    }

}