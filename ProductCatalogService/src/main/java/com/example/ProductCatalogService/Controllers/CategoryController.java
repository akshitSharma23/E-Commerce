package com.example.ProductCatalogService.Controllers;

import com.example.ProductCatalogService.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public String getAllCategory(){
        return "get all product by category controller";
    }

    @GetMapping()
    public String getProductByCategory(){
        return "get a product in category";
    }


}
