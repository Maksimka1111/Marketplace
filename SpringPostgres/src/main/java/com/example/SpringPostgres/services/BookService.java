package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.Book;
import com.example.SpringPostgres.entities.Product;
import com.example.SpringPostgres.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookRepository repository;
    public String readAll(){
        StringBuilder result = new StringBuilder();
        List<Book> productList = repository.findAll();
        for(Book book: productList){
            result.append(book.toString()).append("\n");
        }
        return result.toString();
    }
    public void updateAmount(Long id, int amount){
        repository.getReferenceById(id).setAmount(amount);
        repository.saveAndFlush(repository.getReferenceById(id));
    }
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
