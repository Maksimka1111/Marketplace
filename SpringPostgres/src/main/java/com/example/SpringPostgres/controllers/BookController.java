package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Book;
import com.example.SpringPostgres.services.BookService;
import com.example.SpringPostgres.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService service;
    @Autowired
    ClientService clientService;
    @GetMapping("/readAll")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMIN')")
    public String readAllBook(){
        return service.readAll();
    }
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @GetMapping("/read")
    public String readBook(@RequestParam long id){
        return service.read(id).toString();
    }
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @PostMapping("/create")
    public String createBook(@RequestParam String author, String type, int cost, String name, int amount){
        Book book = new Book();
        book.setAuthor(author);
        book.setTypeOf(type);
        book.setName(name);
        book.setCost(cost);
        book.setSellerId(clientService.getCurrentUser().getId());
        book.setAmount(amount);
        service.create(book);
        return "status: ok";
    }
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @PutMapping("/update")
    public String updateBook(@RequestParam long id, String author, int sellerNum, String type, int cost, String name, int amount){
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setTypeOf(type);
        book.setName(name);
        book.setCost(cost);
        book.setAmount(amount);
        service.update(book);
        return "status: ok";
    }
    @PreAuthorize("hasAnyRole('SELLER', 'ADMIN')")
    @DeleteMapping("/delete")
    public String deleteBook(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
    @GetMapping("/test")
    public String test(){
        return "123";
    }
}
