package com.example.ProductCatalogService.Controllers;

import com.example.ProductCatalogService.AuthenticationClient.AuthClient;
import com.example.ProductCatalogService.DTOs.RequestDTO.ProductRequestDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ProductResponseDTO;
import com.example.ProductCatalogService.DTOs.ResponseDTO.ValidateResponseDTO;
import com.example.ProductCatalogService.Exceptions.ProductNotFound;
import com.example.ProductCatalogService.Models.Role;
import com.example.ProductCatalogService.Models.SessionStatus;
import com.example.ProductCatalogService.Services.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthClient authClient;

    @GetMapping()
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestHeader("Authorization")@Nullable String token,
                                                                   @RequestHeader("userId")@Nullable long id) throws JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        token=token.split(" ")[1];
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        List<String> roles=response.getBody().getRoles();
        boolean isAdmin=false;
        for(String i:roles){
            if(i.equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{pId}")
    public ResponseEntity<ProductResponseDTO> getProduct(@RequestHeader("userId")@Nullable long id,@PathVariable long pId,@RequestHeader("Authorization") String token) throws ProductNotFound, JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        token=token.split(" ")[1];
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        System.out.println(auth.split(" ")[1]);
        ResponseEntity<Optional<ProductResponseDTO>> optionalResponseEntity=productService.getProductById(pId);
        if(optionalResponseEntity.getBody().isEmpty())
            throw new ProductNotFound("product is not available");
        else{
            ResponseEntity<ProductResponseDTO>  response1=new ResponseEntity<>(optionalResponseEntity.getBody().get(),optionalResponseEntity.getStatusCode());
            return response1;
        }
    }

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> addNewProduct(@RequestHeader("Authorization")@Nullable String token,@RequestHeader("userId")@Nullable long id,@RequestBody ProductRequestDTO fakeStoreRequestProductDTO) throws JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        token=token.split(" ")[1];
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        List<String> roles=response.getBody().getRoles();
        boolean isAdmin=false;
        for(String i:roles){
            if(i.equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(productService.createProduct(fakeStoreRequestProductDTO),HttpStatus.OK);
    }

    @PutMapping("/{pId}")
    public ResponseEntity<?> updateProduct(@RequestHeader("Authorization")@Nullable String token,
                                           @RequestHeader("userId")@Nullable long id,@PathVariable long pId, @RequestBody ProductRequestDTO fakeStoreRequestProductDTO) throws JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        token=token.split(" ")[1];
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        List<String> roles=response.getBody().getRoles();
        boolean isAdmin=false;
        for(String i:roles){
            if(i.equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return productService.updateProduct(pId,fakeStoreRequestProductDTO);
    }

    @DeleteMapping("/{pId}")
    public ResponseEntity<?> deleteProduct(@PathVariable long pId,@RequestHeader("Authorization")@Nullable String token,
                                           @RequestHeader("userId")@Nullable long id) throws JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        token=token.split(" ")[1];
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        List<String> roles=response.getBody().getRoles();
        boolean isAdmin=false;
        for(String i:roles){
            if(i.equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return productService.deleteProduct(pId);
    }

    // exception advice class is better option

//    @ExceptionHandler(ProductNotFound.class)
//    public ResponseEntity<ErrorMessageDTO> exceptionHandler(Exception e){
//        ErrorMessageDTO errorMessageDTO=new ErrorMessageDTO();
//        errorMessageDTO.setMsg(e.getMessage());
//        return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
//    }
}
