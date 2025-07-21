package com.erudio.controller;


import com.erudio.data.tdo.BookDto;
import com.erudio.model.Book;
import com.erudio.model.Person;
import com.erudio.service.BookService;
import com.erudio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.erudio.mapper.ObjectMapper.parseObject;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookDto bookDto) {

        var book = parseObject(bookDto, Book.class);

        var person = new Person();
        person.setId(bookDto.getAuthorId());
        book.setPerson(person);

        bookService.save(book);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
