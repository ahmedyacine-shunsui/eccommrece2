
package com.example.ecommerce.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.ecommerce.models.Order;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final ProductService productService;

    // Constructor injection
    public OrderService(
            OrderRepository orderRepository,
            ClientService clientService,
            ProductService productService) {

        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.productService = productService;
    }

    public Order addOrderwithValidation(Order order) {

        // 1. Check product list
        if (order.getProductIds() == null ||
                order.getProductIds().isEmpty()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La liste des produits est vide");
        }

        // 2. Check duplicates
        if (new HashSet<>(order.getProductIds()).size()
                != order.getProductIds().size()) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "La liste contient des produits en double");
        }

        // 3. Check client
        String clientId = order.getClientId();

        if (clientService.getClientById(clientId) == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Client introuvable : " + clientId);
        }

        // 4. Calculate total from actual product prices
        double totalAmount = 0.0;

        for (String productId : order.getProductIds()) {

            Product product =
                    productService.getProductById(productId);

            if (product == null) {
                throw new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Produit introuvable : " + productId);
            }

            totalAmount += product.getPrice();
        }

        // 5. Save order with calculated amount
        Order newOrder = new Order(
                clientId,
                order.getProductIds(),
                totalAmount
        );

        // 6. Return created order
        return orderRepository.save(newOrder);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {

        Order order = orderRepository.findById(id).orElse(null);

        if (order == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Commande introuvable : " + id);
        }

        return order;
    }

    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }
}
