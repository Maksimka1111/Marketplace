package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.products.Book;
import com.example.SpringPostgres.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookService service;

    @GetMapping("/read")
    public String readBook(@RequestParam long id){
        return service.read(id).toString();
    }
    @PostMapping("/create")
    public String createBook(@RequestParam String author, int sellerNum, String type, int cost, String name){
        Book book = new Book();
        book.setAuthor(author);
        book.setTypeOf(type);
        book.setName(name);
        book.setCost(cost);
        book.setSellerNum(sellerNum);
        service.create(book);
        return "status: ok";
    }
    @PutMapping("/update")
    public String updateBook(@RequestParam long id, String author, int sellerNum, String type, int cost, String name){
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setTypeOf(type);
        book.setName(name);
        book.setCost(cost);
        book.setSellerNum(sellerNum);
        service.update(book);
        return "status: ok";
    }
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
