package com.example.ProductCatalogService.Services;



import com.example.ProductCatalogService.DTOs.RequestDTO.ProductRequestDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ProductResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    ResponseEntity<Optional<ProductResponseDTO>> getProductById(long id);

    List<ProductResponseDTO> getAllProducts();

    ProductResponseDTO createProduct(ProductRequestDTO fakeStoreRequestProductDTO);

    ResponseEntity<?> deleteProduct(long id);

    ResponseEntity<ProductResponseDTO> updateProduct(long id, ProductRequestDTO fakeStoreRequestProductDTO);


}