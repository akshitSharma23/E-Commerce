package com.example.ProductCatalogService.Services;

import com.example.ProductCatalogService.DTOs.RequestDTO.ProductRequestDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ProductResponseDTO;
import com.example.ProductCatalogService.Models.Product;
import com.example.ProductCatalogService.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class ProductManagementService implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Optional<ProductResponseDTO>> getProductById(long id) {
        Optional<Product> optional=productRepository.findProductByid(id);
        return optional.isEmpty()?new ResponseEntity<>(HttpStatus.NOT_FOUND):new ResponseEntity<>(Optional.of(ProductResponseDTO.getInstance(optional.get())),HttpStatus.OK);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> list=productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS=new ArrayList<>();
        for(Product i:list){
            productResponseDTOS.add(ProductResponseDTO.getInstance(i));
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO fakeStoreRequestProductDTO) {
        Product product=Product.getInstance(fakeStoreRequestProductDTO);
        product=productRepository.save(product);
        return ProductResponseDTO.getInstance(product);
    }

    @Override
    public ResponseEntity<?> deleteProduct(long id) {
        Optional<Product> optional=productRepository.findProductByid(id);
        if(optional.isEmpty())return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Product product=optional.get();
        product.setDeleted(true);
        productRepository.save(product);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> updateProduct(long id, ProductRequestDTO productRequestDTO) {
        Product product=Product.getInstance(productRequestDTO);
        product.setId(id);
        productRepository.save(product);
        return new ResponseEntity<>(ProductResponseDTO.getInstance(product),HttpStatus.OK);
    }
}
