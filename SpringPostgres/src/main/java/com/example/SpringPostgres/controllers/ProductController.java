package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Product;
import com.example.SpringPostgres.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService service;

    @GetMapping("show")
    public String showAllProducts(){
        return service.readAll();
    }

    @GetMapping("/read")
    public String readProduct(@RequestParam long id){
        return service.read(id).toString();
    }

    @PostMapping("/create")
    public String createProduct(@RequestParam String author,int volume, int sellerNum, String type, int cost, String name){
        Product product = new Product();
        product.setAuthor(author);
        product.setTypeOf(type);
        product.setName(name);
        product.setCost(cost);
        product.setSellerNum(sellerNum);
        product.setVolume(volume);
        //product.setOrder(new Order());
        service.create(product);
        return "status: ok";
    }
    @PutMapping("/update")
    public String updateProduct(@RequestParam long id, String author, int volume, int sellerNum, String type, int cost, String name){
        Product product = new Product();
        product.setId(id);
        product.setAuthor(author);
        product.setTypeOf(type);
        product.setName(name);
        product.setCost(cost);
        product.setSellerNum(sellerNum);
        product.setVolume(volume);
        //product.setOrder(null);
        service.update(product);
        return "status: ok";
    }
    @DeleteMapping("/delete")
    public String deleteProduct(@RequestParam long id) {
        service.delete(id);
        return "status: ok";
    }
}
