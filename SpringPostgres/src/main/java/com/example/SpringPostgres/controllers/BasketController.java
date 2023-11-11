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
        boolean success = true;
        var orders = service.getAll();
        for(Order order: orders){
            if (order.getProduct().getAvailable() < order.getAmount()){
                success = false;
                break;
            }
        }
        if (success) {
            String result = service.readAll(client_id);
            service.clear();
            return "Your orders:\n" + result;
        }
        return "the checkout failed";
    }

    @PostMapping("/add")
    public String addOrder(@RequestParam Long product_id, Long client_id){
        if (productService.read(product_id).getAvailable() > 0) {
            Order order = new Order();
            order.setAmount(1);
            order.setProduct(productService.read(product_id));
            order.setClient(clientService.read(client_id));
            productService.read(product_id).setAvailable(productService.read(product_id).getAvailable() - 1);
            service.create(order);
            return "status: ok";
        } return "no such product";
    }

    @PutMapping("/changeAmount")
    public String changeAmountOfProduct(@RequestParam long id, int amount){
        int a = productService.read(service.read(id).getProduct().getId()).getAvailable();
        if (a >= amount) {
            productService.read(service.read(id).getProduct().getId()).setAvailable(a - amount);
            service.update(id, amount);
            return "status: ok";
        } return "no such amount of product";
    }

    @DeleteMapping("/delete")
    public String deleteOrder(@RequestParam long id){
        int a = productService.read(service.read(id).getProduct().getId()).getAvailable();
        productService.read(service.read(id).getProduct().getId()).setAvailable(a + 1);
        service.delete(id);
        return "status: ok";
    }
}
