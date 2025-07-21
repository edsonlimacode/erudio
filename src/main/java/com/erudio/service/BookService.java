package com.erudio.service;


import com.erudio.exception.exceptions.NotFoundExecption;
import com.erudio.model.Book;
import com.erudio.respository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    public void save(Book book) {
        bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(Long id) {

        return bookRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Livro não encontrado"));

    }

    public void delete(Long id){

        var person = bookRepository.findById(id).orElseThrow(() -> new NotFoundExecption("Livro não encontrado"));

        bookRepository.delete(person);

    }


}
