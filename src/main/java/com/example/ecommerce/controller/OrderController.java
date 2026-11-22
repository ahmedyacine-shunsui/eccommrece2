
package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.ecommerce.models.Order;
import com.example.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // Create order
    @PostMapping
    public ResponseEntity<Order> addOrder(
            @RequestBody Order order) {

        Order savedOrder =
                orderService.addOrderwithValidation(order);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedOrder);
    }

    // Get all orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // Get order by ID
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id);
    }
}
