package com.iprwc.iprwc_backend_project.controller;

import com.iprwc.iprwc_backend_project.model.Product;
import com.iprwc.iprwc_backend_project.service.ProductService;
import com.iprwc.iprwc_backend_project.service.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController
{
    private final ProductService productService;

    public ProductController(ProductService productService)
    {
        this.productService = productService;
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
    public Product saveProduct(@RequestParam String id, @RequestParam String name, @RequestParam Double cost, @RequestParam byte[] imageData)
    {
        Product product = new Product(id, name, cost, imageData);
        return productService.save(product);
    }

    @DeleteMapping("products/{id}")
    public Product deleteAccount(@PathVariable String id)
    {
        if (productService.findById(id).isPresent())
        {
            Product product = productService.findById(id).get();
            productService.delete(product);
            return product;
        }else {
            return null;
        }

    }

}
