package com.example.SpringPostgres.services;

import com.example.SpringPostgres.entities.Order;
import com.example.SpringPostgres.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    ProductService productService;

    public void create(Order order){
        repository.save(order);
    };


    public Order read(long id){
        return repository.findById(id).get();
    }
    public String readAll(Long id){
        StringBuilder result = new StringBuilder();
        List<Order> orderList = repository.findAll();
        for(Order order: orderList){
            if (id.equals(order.getClient().getId()))
                result.append(order.toString()).append("\n");
        }
        return result.toString();
    }
    public List<Order> getAll(){
        return repository.findAll();
    }
    public void update(Long id, int amount){
        Order order = repository.getReferenceById(id);
        order.setAmount(amount);
        repository.saveAndFlush(order);
    }
    public void delete(long id){
        if (repository.findById(id).isPresent())
            repository.delete(repository.findById(id).get());
    }
    public void clear(){
        List<Order> orders = repository.findAll();
        for(Order order: orders){
            productService.delete(order.getProduct().getId());
        }
        repository.deleteAll();
    }
}
