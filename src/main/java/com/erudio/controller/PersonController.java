package com.erudio.controller;

import com.erudio.controller.swagger.PersonControllerDoc;
import com.erudio.data.tdo.CustomPageResponse;
import com.erudio.data.tdo.PersonDto;
import com.erudio.model.Person;
import com.erudio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.erudio.mapper.ObjectMapper.parseListObject;
import static com.erudio.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/person")
public class PersonController implements PersonControllerDoc {

    @Autowired
    private PersonService personService;

    @PostMapping
    @Override
    public ResponseEntity<?> create(@RequestBody PersonDto personDto) {
        var person = parseObject(personDto, Person.class);
        personService.create(person);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<PersonDto> findById(@PathVariable Long id) {

        var personDto = parseObject(personService.findById(id), PersonDto.class);

        addHateoasLinks(personDto);

        return ResponseEntity.ok(personDto);
    }

    @GetMapping(value = "/all", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<List<PersonDto>> findAll() {

        var personList = parseListObject(personService.getAll(), PersonDto.class);

        personList.forEach(this::addHateoasLinks);

        return ResponseEntity.ok(personList);
    }

    @GetMapping(value = "/with/pagination", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomPageResponse<PersonDto>> findAllPaginate(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "15") Integer size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));

        Page<Person> allPersonPaginated = personService.getAllPaginate(pageable);

        var personsListDto = allPersonPaginated.map(person -> {
            var personDtoResult = parseObject(person, PersonDto.class);
            addHateoasLinks(personDtoResult);

            return personDtoResult;
        });

        CustomPageResponse<PersonDto> customResponse = new CustomPageResponse<>(personsListDto);
        return ResponseEntity.ok(customResponse);
    }

    @GetMapping(value = "/search/by-name/{firstName}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<CustomPageResponse<PersonDto>> findAllPaginate(
            @PathVariable("firstName") String firstName,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "15") Integer size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));

        Page<Person> allPersonPaginated = personService.findByFirstName(firstName, pageable);

        var personsListDto = allPersonPaginated.map(person -> {
            var personDtoResult = parseObject(person, PersonDto.class);
            addHateoasLinks(personDtoResult);

            return personDtoResult;
        });

        CustomPageResponse<PersonDto> customResponse = new CustomPageResponse<>(personsListDto);
        return ResponseEntity.ok(customResponse);
    }


    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Override
    public ResponseEntity<PersonDto> update(@RequestBody PersonDto personDto) {

        var person = parseObject(personDto, Person.class);

        var personDtoResult = parseObject(personService.update(person), PersonDto.class);

        addHateoasLinks(personDtoResult);

        return ResponseEntity.ok(personDtoResult);
    }

    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<?> remove(@PathVariable Long id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/desable/{id}")
    public void desable(@PathVariable Long id) {
        personService.disable(id);
    }

    @PutMapping("/enable/{id}")
    public void endable(@PathVariable Long id) {
        personService.enable(id);
    }

    private void addHateoasLinks(PersonDto personDto) {
        personDto.add(linkTo(methodOn(PersonController.class).findById(personDto.getId())).withSelfRel().withType("GET"));
        personDto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        personDto.add(linkTo(methodOn(PersonController.class).remove(personDto.getId())).withRel("remove").withType("DELETE"));
        personDto.add(linkTo(methodOn(PersonController.class).create(personDto)).withRel("create").withType("POST"));
        personDto.add(linkTo(methodOn(PersonController.class).update(personDto)).withRel("update").withType("PUT"));
    }
}
