package com.erudio.service;

import com.erudio.mocks.MockPerson;
import com.erudio.model.Person;
import com.erudio.respository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    MockPerson input;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @BeforeEach
    void setUp() {

        input = new MockPerson();
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void create() {

        Person person = input.mockEntity(1);

        when(personRepository.save(person));

        personService.create(person);

    }

    @Test
    void getAll() {
    }

    @Test
    void findById() {
        Person person = input.mockEntity(1);
        when(personRepository.findById(1L)).thenReturn(Optional.of(person));

        Person personResult = personService.findById(1L);
        personResult.setCreatedAt(new Date());

        assertNotNull(personResult);
        assertNotNull(personResult.getId());
        assertNotNull(personResult.getFirstName());
        assertNotNull(personResult.getLastName());
        assertNotNull(personResult.getCreatedAt());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}