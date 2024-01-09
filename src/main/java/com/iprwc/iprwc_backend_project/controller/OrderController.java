package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.*;
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
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

    private final OrderService orderService;
    private final ProductInShoppingCartController productInShoppingCartController;
    private final AccountController accountController;

    public OrderController(OrderService orderService, ProductInShoppingCartController productInShoppingCartController, AccountController accountController) {
        this.orderService = orderService;
        this.productInShoppingCartController = productInShoppingCartController;
        this.accountController = accountController;
    }

    @GetMapping
    public ResponseEntity<FrontendOrder[]> getOrders(@RequestHeader(name = "X-Account-Id", required = false) String accountId) {
        if (accountId == null) {
            System.out.println("No id was delivered");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Account account;
        if (!this.accountController.checkIfAccountExists(accountId)) {
            System.out.println("Account does not exist");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }else {
            account = this.accountController.getById(accountId);
        }

        RoleType role = account.getRole();
        if (Objects.requireNonNull(role) == RoleType.Admin) {
            BackendOrder[] backendOrders = this.orderService.findAll().toArray(new BackendOrder[0]);
            FrontendOrder[] frontendOrders = Arrays.stream(backendOrders)
                    .map(this::mapToFrontend)
                    .toArray(FrontendOrder[]::new);
            return ResponseEntity.ok(frontendOrders);
        }
        System.out.println("Account is no admin");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<FrontendOrder> createOrder(@RequestBody FrontendOrder order) {
        System.out.println("Frontend Order: " + order);

        BackendOrder backendOrder = mapToBackend(order);
        System.out.println("Backend Order: " + backendOrder);

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
