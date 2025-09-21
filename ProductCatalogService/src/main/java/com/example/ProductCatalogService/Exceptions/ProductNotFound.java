package com.example.ProductCatalogService.Exceptions;

import com.example.ProductCatalogService.Models.Product;

public class ProductNotFound extends Exception{

    public ProductNotFound(String msg){
        super(msg);
    }
}
