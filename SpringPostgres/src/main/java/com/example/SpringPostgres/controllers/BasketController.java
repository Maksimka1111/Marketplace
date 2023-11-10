package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Order;
import com.example.SpringPostgres.services.ClientService;
import com.example.SpringPostgres.services.OrderService;
import com.example.SpringPostgres.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/basket")
public class BasketController {
    @Autowired
    OrderService service;
    @Autowired
    ProductService productService;
    @Autowired
    ClientService clientService;

    @GetMapping("/show")
    public String showBasket(@RequestParam Long client_id){
        return service.readAll(client_id);
    }
    @GetMapping("/checkout")
    public String checkout(@RequestParam Long client_id){
        String result = service.readAll(client_id);
        service.clear();
        return "Your orders:\n" + result;
    }

    @PostMapping("/add")
    public String addOrder(@RequestParam Long product_id, Long client_id){
        Order order = new Order();
        order.setAmount(1);
        order.setProduct(productService.read(product_id));
        order.setClient(clientService.read(client_id));
        service.create(order);
        return "status: ok";
    }

    @PutMapping("/changeAmount")
    public String changeAmountOfProduct(@RequestParam long id, int amount){
        service.update(id, amount);
        return "status: ok";
    }

    @DeleteMapping("/delete")
    public String deleteOrder(@RequestParam long id){
        service.delete(id);
        return "status: ok";
    }
}
