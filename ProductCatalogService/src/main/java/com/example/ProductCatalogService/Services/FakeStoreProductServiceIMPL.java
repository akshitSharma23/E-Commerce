package com.example.ProductCatalogService.Services;

import com.example.ProductCatalogService.DTOs.RequestDTO.ProductRequestDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
//@Primary
public class FakeStoreProductServiceIMPL implements ProductService{

    @Autowired
    private RestTemplate restTemplate;

    private final String ApiEndPoint ="https://fakestoreapi.com/products";

    @Override
    public ResponseEntity<Optional<ProductResponseDTO>> getProductById(long id) {
        ResponseEntity<ProductResponseDTO> responseEntity=restTemplate.exchange(ApiEndPoint +"/"+id, HttpMethod.GET,null, ProductResponseDTO.class);
        Optional<ProductResponseDTO> optional=responseEntity.getBody()==null?Optional.empty():Optional.of(responseEntity.getBody());
        ResponseEntity<Optional<ProductResponseDTO>> optionalResponseEntity=new ResponseEntity<>(optional,responseEntity.getStatusCode());
        return optionalResponseEntity;
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        ResponseEntity<ProductResponseDTO[]> responseEntity=restTemplate.exchange(ApiEndPoint,HttpMethod.GET,null, ProductResponseDTO[].class);
        List<ProductResponseDTO> productResponseDTOS =new ArrayList<>();
        for(ProductResponseDTO i:responseEntity.getBody()){
            productResponseDTOS.add(i);
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO fakeStoreRequestProductDTO) {
        HttpEntity<ProductRequestDTO> httpEntity=new HttpEntity<>(fakeStoreRequestProductDTO);
        ResponseEntity<ProductResponseDTO> responseEntity=restTemplate.exchange(ApiEndPoint,HttpMethod.POST,httpEntity, ProductResponseDTO.class);
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<?> deleteProduct(long id) {
        return restTemplate.exchange(ApiEndPoint+"/"+id,HttpMethod.DELETE,HttpEntity.EMPTY,void.class);
    }

    @Override
    public ResponseEntity<ProductResponseDTO> updateProduct(long id, ProductRequestDTO fakeStoreRequestProductDTO) {
        HttpEntity<ProductRequestDTO> httpEntity=new HttpEntity<>(fakeStoreRequestProductDTO);
        ResponseEntity<ProductResponseDTO> responseEntity=restTemplate.exchange(ApiEndPoint+"/"+id,HttpMethod.PUT,httpEntity, ProductResponseDTO.class);
        return responseEntity;
    }
}















