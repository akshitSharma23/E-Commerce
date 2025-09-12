package com.example.ProductCatalogService.Services;

import com.example.ProductCatalogService.DTOs.RequestDTO.FakeStoreRequestProductDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.FakeStoreResponseProductDTO;
import com.example.ProductCatalogService.Models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakeStoreProductServiceIMPL implements ProductService{

    @Autowired
    private RestTemplate restTemplate;

    private final String ApiEndPoint ="https://fakestoreapi.com/products";

    @Override
    public Product getProductById(long id) {
        ResponseEntity<FakeStoreRequestProductDTO> responseEntity=restTemplate.exchange(ApiEndPoint +"/"+id, HttpMethod.GET,null, FakeStoreRequestProductDTO.class);
        Product product =Product.getInstance(responseEntity.getBody());
        return product;
    }

    @Override
    public List<FakeStoreResponseProductDTO> getAllProducts() {
        ResponseEntity<FakeStoreResponseProductDTO[]> responseEntity=restTemplate.exchange(ApiEndPoint,HttpMethod.GET,null, FakeStoreResponseProductDTO[].class);
        List<FakeStoreResponseProductDTO> fakeStoreResponseProductDTOS=new ArrayList<>();
        for(FakeStoreResponseProductDTO i:responseEntity.getBody()){
            fakeStoreResponseProductDTOS.add(i);
        }
        return fakeStoreResponseProductDTOS;
    }

    @Override
    public FakeStoreResponseProductDTO createProduct(FakeStoreRequestProductDTO fakeStoreRequestProductDTO) {
        HttpEntity<FakeStoreRequestProductDTO> httpEntity=new HttpEntity<>(fakeStoreRequestProductDTO);
        ResponseEntity<FakeStoreResponseProductDTO> responseEntity=restTemplate.exchange(ApiEndPoint,HttpMethod.POST,httpEntity, FakeStoreResponseProductDTO.class);
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<?> deleteProduct(long id) {
        return restTemplate.exchange(ApiEndPoint+"/"+id,HttpMethod.DELETE,HttpEntity.EMPTY,void.class);
    }

    @Override
    public ResponseEntity<FakeStoreResponseProductDTO> updateProduct(long id, FakeStoreRequestProductDTO fakeStoreRequestProductDTO) {
        HttpEntity<FakeStoreRequestProductDTO> httpEntity=new HttpEntity<>(fakeStoreRequestProductDTO);
        ResponseEntity<FakeStoreResponseProductDTO> responseEntity=restTemplate.exchange(ApiEndPoint+"/"+id,HttpMethod.PUT,httpEntity,FakeStoreResponseProductDTO.class);
        return responseEntity;
    }
}















