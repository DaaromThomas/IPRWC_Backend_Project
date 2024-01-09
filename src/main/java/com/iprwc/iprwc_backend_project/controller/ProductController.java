package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.Account;
import com.iprwc.iprwc_backend_project.model.Product;
import com.iprwc.iprwc_backend_project.model.RoleType;
import com.iprwc.iprwc_backend_project.service.ProductService;
import com.iprwc.iprwc_backend_project.service.ProductServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController
{
    private final ProductService productService;
    private final AccountController accountController;

    public ProductController(ProductService productService, AccountController accountController) {
        this.productService = productService;
        this.accountController = accountController;
    }

    @GetMapping("/products")
    public List<Product> findAllProducts()
    {
        return productService.findAll();
    }

    @GetMapping("/products/id/{id}")
    public Product findById(@PathVariable String id)
    {
        if (productService.findById(id).isPresent()){
            return productService.findById(id).get();
        }else {
            return null;
        }
    }

    @GetMapping("/products/name/{name}")
    public Product findByName(@PathVariable String name)
    {
        if (productService.findByName(name).isPresent()){
            return productService.findByName(name).get();
        }else {
            return null;
        }
    }

    @PostMapping("/products")
    public Product saveProduct(@RequestBody Product product, @RequestHeader(name = "X-Account-Id", required = false) String accountId)
    {
        if (accountId == null) {
            System.out.println("No id was delivered");
            return null;
        }

        Account account;
        if (!this.accountController.checkIfAccountExists(accountId)) {
            System.out.println("Account does not exist");
            return null;
        }else {
            account = this.accountController.getById(accountId);
        }

        RoleType role = account.getRole();
        if (role.equals(RoleType.Admin)){
            return productService.save(product);
        } else {
            return null;
        }
    }

    @DeleteMapping("products/{id}")
    public Product deleteProduct(@PathVariable String id, @RequestHeader(name = "X-Account-Id", required = false) String accountId)
    {
        if (accountId == null) {
            System.out.println("No id was delivered");
            return null;
        }

        Account account;
        if (!this.accountController.checkIfAccountExists(accountId)) {
            System.out.println("Account does not exist");
            return null;
        }else {
            account = this.accountController.getById(accountId);
        }

        RoleType role = account.getRole();
        if (role.equals(RoleType.Admin)){
            if (productService.findById(id).isPresent())
            {
                Product product = productService.findById(id).get();
                productService.delete(product);
                return product;
            }else {
                return null;
            }
        } else {
            return null;
        }


    }

}
