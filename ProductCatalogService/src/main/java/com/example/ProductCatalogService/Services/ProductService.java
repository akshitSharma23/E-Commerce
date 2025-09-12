package com.example.ProductCatalogService.Services;



import com.example.ProductCatalogService.DTOs.RequestDTO.FakeStoreRequestProductDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.FakeStoreResponseProductDTO;
import com.example.ProductCatalogService.Models.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);

    List<FakeStoreResponseProductDTO> getAllProducts();

    FakeStoreResponseProductDTO createProduct(FakeStoreRequestProductDTO fakeStoreRequestProductDTO);

    ResponseEntity<?> deleteProduct(long id);

    ResponseEntity<FakeStoreResponseProductDTO> updateProduct(long id, FakeStoreRequestProductDTO fakeStoreRequestProductDTO);


}