package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.products.Book;
import com.example.SpringPostgres.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public void create(Book book){
        repository.save(book);
    };
    public Book read(long id){
        return repository.findById(id).get();
    }
    public void update(Book book){
        repository.saveAndFlush(book);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
}
