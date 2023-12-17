package com.example.SpringPostgres.controllers;

import com.example.SpringPostgres.entities.Order;
import com.example.SpringPostgres.entities.Product;
import com.example.SpringPostgres.entities.ProductType;
import com.example.SpringPostgres.entities.WashingMachine;
import com.example.SpringPostgres.services.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client/basket")
public class BasketController {
    @Autowired
    OrderService service;
    @Autowired
    BookService bookService;
    @Autowired
    TelephoneService telephoneService;
    @Autowired
    MachineService machineService;
    @Autowired
    ClientService clientService;

    @GetMapping("/show")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMINISTRATOR')")
    public String showBasket(){
        return service.readAll(clientService.getCurrentUser().getId());
    }
    @GetMapping("/checkout")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMINISTRATOR')")
    public String checkout(){
        String result = service.readAll(clientService.getCurrentUser().getId());
        service.clear();
        return "Your orders:\n" + result;
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMINISTRATOR')")
    public String addOrder(@RequestParam ProductType productType, Long product_id, int amount){
        if (productType.equals(ProductType.BOOK) && bookService.read(product_id) == null){
            return "no such book";
        }
        else if (bookService.read(product_id) != null && productType.equals(ProductType.BOOK) && bookService.read(product_id).getAmount() < amount){
            return "no such amount of books";
        }
        else if(bookService.read(product_id) != null && productType.equals(ProductType.BOOK)){
            bookService.updateAmount(product_id, bookService.read(product_id).getAmount() - amount);
        }
        if (productType.equals(ProductType.TELEPHONE) && telephoneService.read(product_id) == null){
            return "no such telephone";
        }
        else if (telephoneService.read(product_id) != null && productType.equals(ProductType.TELEPHONE) && telephoneService.read(product_id).getAmount() < amount){
            return "no such amount of telephones";
        }
        else if(telephoneService.read(product_id) != null && productType.equals(ProductType.TELEPHONE)){
            telephoneService.updateAmount(product_id, telephoneService.read(product_id).getAmount() - amount);
        }
        if (productType.equals(ProductType.WASHING_MACHINE) && machineService.read(product_id) == null){
            return "no such washing machine";
        }
        else if (machineService.read(product_id) != null && productType.equals(ProductType.WASHING_MACHINE) && machineService.read(product_id).getAmount() < amount){
            return "no such amount of washing machines";
        }
        else if(machineService.read(product_id) != null && productType.equals(ProductType.WASHING_MACHINE)){
           machineService.updateAmount(product_id, machineService.read(product_id).getAmount() - amount);
        }
        Product product = new Product();
        product.setProductId(product_id);
        product.setProductType(productType);
        Order order = new Order();
        order.setProduct(product);
        order.setClient(clientService.getCurrentUser());
        order.setAmount(amount);
        service.create(order);
        clientService.addOrder(order);
        return "status: ok";
    }

    @PutMapping("/changeAmount")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMINISTRATOR')")
    public String changeAmountOfProduct(@RequestParam long id, int amount){
        Order order = service.read(id);
        ProductType productType = order.getProduct().getProductType();
        Long product_id = order.getProduct().getProductId();
        if (bookService.read(product_id) != null && productType.equals(ProductType.BOOK) && bookService.read(product_id).getAmount() < amount){
            return "no such amount of books";
        }
        else if(bookService.read(product_id) != null && productType.equals(ProductType.BOOK)){
            bookService.updateAmount(product_id, bookService.read(product_id).getAmount() - amount);
        }
        if (telephoneService.read(product_id) != null && productType.equals(ProductType.TELEPHONE) && telephoneService.read(product_id).getAmount() < amount){
            return "no such amount of telephones";
        }
        else if(telephoneService.read(product_id) != null && productType.equals(ProductType.TELEPHONE)){
            telephoneService.updateAmount(product_id, telephoneService.read(product_id).getAmount() - amount);
        }
        if (machineService.read(product_id) != null && productType.equals(ProductType.WASHING_MACHINE) && machineService.read(product_id).getAmount() < amount){
            return "no such amount of washing machines";
        }
        else if(machineService.read(product_id) != null && productType.equals(ProductType.WASHING_MACHINE)){
            machineService.updateAmount(product_id, machineService.read(product_id).getAmount() - amount);
        }

        service.update(id, amount);

        return "status: ok";
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyRole('USER', 'SELLER', 'ADMINISTRATOR')")
    public String deleteOrder(@RequestParam long id){
        Order order = service.read(id);
        ProductType productType = order.getProduct().getProductType();
        Long product_id = order.getProduct().getProductId();

        if (productType.equals(ProductType.BOOK)){
            bookService.updateAmount(product_id, bookService.read(product_id).getAmount() + order.getAmount());
        }
        if (productType.equals(ProductType.TELEPHONE)){
            telephoneService.updateAmount(product_id, telephoneService.read(product_id).getAmount() + order.getAmount());
        }
        if (productType.equals(ProductType.WASHING_MACHINE)){
            machineService.updateAmount(product_id, machineService.read(product_id).getAmount() + order.getAmount());
        }
        clientService.delOrder(order);
        service.delete(id);
        return "status: ok";
    }
}
