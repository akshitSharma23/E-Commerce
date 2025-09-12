package com.example.ProductCatalogService.Controllers;

import com.example.ProductCatalogService.DTOs.RequestDTO.FakeStoreRequestProductDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.FakeStoreResponseProductDTO;
import com.example.ProductCatalogService.Models.Product;
import com.example.ProductCatalogService.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public String getAllProducts(){
        return "get all products";
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable long id){
        return productService.getProductById(id);
    }

    @PostMapping()
    public FakeStoreResponseProductDTO addNewProduct(@RequestBody FakeStoreRequestProductDTO fakeStoreRequestProductDTO){
        return productService.createProduct(fakeStoreRequestProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody FakeStoreRequestProductDTO fakeStoreRequestProductDTO){
        return productService.updateProduct(id,fakeStoreRequestProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }
}
