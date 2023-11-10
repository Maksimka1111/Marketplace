package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.Product;
import com.example.SpringPostgres.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository repository;

    public void create(Product product){
        repository.save(product);
    };
    public Product read(long id){
        return repository.findById(id).get();
    }
    public String readAll(){
        StringBuilder result = new StringBuilder();
        List<Product> productList = repository.findAll();
        for(Product product: productList){
            result.append(product.toString()).append("\n");
        }
        return result.toString();
    }
    public void update(Product product){
        repository.saveAndFlush(product);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
}
