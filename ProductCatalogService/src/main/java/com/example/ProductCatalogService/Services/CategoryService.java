package com.example.ProductCatalogService.Services;

import com.example.ProductCatalogService.Models.Product;

public interface CategoryService {

    String getAllCategory();

    String getProductsByCategory();

    String addProductByCategory(String str, Product product);
}
