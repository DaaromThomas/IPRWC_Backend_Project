package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.*;
import com.iprwc.iprwc_backend_project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderService orderService;
    private final ProductInShoppingCartController productInShoppingCartController;

    public OrderController(OrderService orderService, ProductInShoppingCartController productInShoppingCartController) {
        this.orderService = orderService;
        this.productInShoppingCartController = productInShoppingCartController;
    }

    @PostMapping
    public ResponseEntity<FrontendOrder> createOrder(@RequestBody FrontendOrder frontendOrder) {
        System.out.println(frontendOrder.getId() + "\n" + frontendOrder.getCustomer()+ "\n" + Arrays.toString(frontendOrder.getProducts()) + "\n" + frontendOrder.getName());

        BackendOrder backendOrder = rewriteOrder(frontendOrder);
        System.out.println(backendOrder.getId());

        orderService.save(backendOrder);
        productInShoppingCartController.saveProducts(backendOrder, frontendOrder);

        return ResponseEntity.ok(frontendOrder);
    }

    private BackendOrder rewriteOrder(FrontendOrder frontendOrder) {
        return new BackendOrder(frontendOrder.getId(), frontendOrder.getName(), frontendOrder.getCustomer(), Arrays.asList(frontendOrder.getProducts()));
    }
}
