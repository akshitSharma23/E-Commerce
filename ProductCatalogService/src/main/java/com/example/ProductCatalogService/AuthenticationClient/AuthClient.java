package com.example.ProductCatalogService.AuthenticationClient;

import com.example.ProductCatalogService.DTOs.RequestDTO.ValidateRequestDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ValidateResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AuthClient {

    @Autowired
    private RestTemplate restTemplate;

    private final String ApiEndPoint="http://localhost:9000/auth/validate/";

    public ResponseEntity<ValidateResponseDTO> validate(String token,long userId) throws JsonProcessingException {
        HttpHeaders httpHeaders=new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization",token);
        HttpEntity<String> requestEntity= new HttpEntity<String>(httpHeaders);
        ResponseEntity<ValidateResponseDTO> response=restTemplate.exchange(ApiEndPoint+userId, HttpMethod.GET,requestEntity,ValidateResponseDTO.class);
        return response;
    }


}
