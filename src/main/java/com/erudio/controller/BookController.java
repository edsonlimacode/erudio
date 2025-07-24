package com.erudio.controller;


import com.erudio.controller.swagger.BookControllerDoc;
import com.erudio.data.tdo.BookDto;
import com.erudio.data.tdo.CustomPageResponse;
import com.erudio.model.Book;
import com.erudio.model.Person;
import com.erudio.service.BookService;
import com.erudio.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.erudio.mapper.ObjectMapper.parseListObject;
import static com.erudio.mapper.ObjectMapper.parseObject;

@RestController
@RequestMapping("/books")
public class BookController implements BookControllerDoc {

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

    @GetMapping
    public ResponseEntity<List<BookDto>> findAll() {

        var bookDto = parseListObject(bookService.findAll(), BookDto.class);

        return ResponseEntity.ok(bookDto);
    }

    @GetMapping("/with/pagination")
    public ResponseEntity<CustomPageResponse<BookDto>> findAllPaginate(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "15") Integer size,
            @RequestParam(name = "direction", defaultValue = "asc") String direction
    ) {

        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "id"));

        var allBooksPaged = bookService.findAll(pageable);

        var bookDtoList = allBooksPaged.map(book -> parseObject(book, BookDto.class));

        var customPageResponse = new CustomPageResponse<>(bookDtoList);

        return ResponseEntity.ok(customPageResponse);
    }

    @GetMapping("{id}")
    public ResponseEntity<BookDto> findById(@PathVariable Long id) {

        var bookDto = parseObject(bookService.findById(id), BookDto.class);

        return ResponseEntity.ok(bookDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {

        bookService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<BookDto> update(@RequestBody BookDto bookDto) {

        var book = parseObject(bookDto, Book.class);

        var person = new Person();
        person.setId(bookDto.getAuthorId());
        book.setPerson(person);


        var bookResult = bookService.update(book);

        return ResponseEntity.ok(parseObject(bookResult, BookDto.class));
    }
}
