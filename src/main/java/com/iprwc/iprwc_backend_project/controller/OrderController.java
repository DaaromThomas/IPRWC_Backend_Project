package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.*;
import com.iprwc.iprwc_backend_project.security.AccountChecker;
import com.iprwc.iprwc_backend_project.service.OrderService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;
    private final ProductInShoppingCartController productInShoppingCartController;
    private final AccountChecker accountChecker;

    public OrderController(OrderService orderService, ProductInShoppingCartController productInShoppingCartController, AccountChecker accountChecker) {
        this.orderService = orderService;
        this.productInShoppingCartController = productInShoppingCartController;
        this.accountChecker = accountChecker;
    }

    @GetMapping
    public ResponseEntity<FrontendOrder[]> getOrders(@RequestHeader(name = "X-Account-Id", required = false) String accountId) {
        if (!this.accountChecker.checkIfAccountExists(accountId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        BackendOrder[] backendOrders = this.orderService.findAll().toArray(new BackendOrder[0]);
        FrontendOrder[] frontendOrders = Arrays.stream(backendOrders)
                .map(this::mapToFrontend)
                .toArray(FrontendOrder[]::new);
        return ResponseEntity.ok(frontendOrders);
    }

    @PostMapping
    public ResponseEntity<FrontendOrder> createOrder(@RequestBody FrontendOrder order) {
        BackendOrder backendOrder = mapToBackend(order);

        orderService.save(backendOrder);
        productInShoppingCartController.saveProducts(backendOrder, order);

        return ResponseEntity.ok(order);
    }


    private BackendOrder mapToBackend(FrontendOrder frontendOrder) {
        return new BackendOrder(frontendOrder.getId(), frontendOrder.getName(), frontendOrder.getCustomer(), frontendOrder.getCustomerName(), frontendOrder.getEmail(), frontendOrder.getAddress());
    }

    private FrontendOrder mapToFrontend(BackendOrder backendOrder){
        String id = backendOrder.getId();
        String name = backendOrder.getName();
        String customer = backendOrder.getCustomer();
        String customerName = backendOrder.getCustomerName();
        String email = backendOrder.getEmail();
        String address = backendOrder.getAddress();
        ProductInShoppingCart[] products;

        products = findProductsByOrder(id);

        return new FrontendOrder(id, name, customer, products, customerName, email, address);
    }

    private ProductInShoppingCart[] findProductsByOrder(String id){
        return this.productInShoppingCartController.getProductsByOrderId(id);
    }
}
