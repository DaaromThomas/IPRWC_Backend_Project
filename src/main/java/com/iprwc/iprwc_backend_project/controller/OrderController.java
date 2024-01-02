package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.*;
import com.iprwc.iprwc_backend_project.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

    @GetMapping
    public ResponseEntity<FrontendOrder[]> getOrders(){
        BackendOrder[] backendOrders = this.orderService.findAll().toArray(new BackendOrder[0]);
        ArrayList<FrontendOrder> frontendOrders = new ArrayList<>();

        for(BackendOrder backendOrder : backendOrders){
            FrontendOrder frontendOrder = mapToFrontend(backendOrder);
            frontendOrders.add(frontendOrder);
        }

        FrontendOrder[] frontendOrderArray = frontendOrders.toArray(new FrontendOrder[0]);

        return ResponseEntity.ok(frontendOrderArray);
    }

    @PostMapping
    public ResponseEntity<FrontendOrder> createOrder(@RequestBody FrontendOrder order) {
        System.out.println("Frontend Order: " + order); // Print frontendOrder values

        BackendOrder backendOrder = mapToBackend(order);
        System.out.println("Backend Order: " + backendOrder); // Print backendOrder values

        orderService.save(backendOrder);
        productInShoppingCartController.saveProducts(backendOrder, order);

        return ResponseEntity.ok(order);
    }


    private BackendOrder mapToBackend(FrontendOrder frontendOrder) {
        return new BackendOrder(frontendOrder.getId(), frontendOrder.getName(), frontendOrder.getCustomer());
    }

    private FrontendOrder mapToFrontend(BackendOrder backendOrder){
        String id = backendOrder.getId();
        String name = backendOrder.getName();
        String customer = backendOrder.getCustomer();
        ProductInShoppingCart[] products;

        products = findProductsByOrder(id);

        return new FrontendOrder(id, name, customer, products);
    }

    private ProductInShoppingCart[] findProductsByOrder(String id){
        return this.productInShoppingCartController.getProductsByOrderId(id);
    }
}
