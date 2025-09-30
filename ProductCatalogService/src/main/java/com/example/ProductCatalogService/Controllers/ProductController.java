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
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(@RequestHeader("AUTH_TOKEN")@Nullable String token,
                                                                   @RequestHeader("userId")@Nullable long id) throws JsonProcessingException {
        if(token==null)return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        ResponseEntity<ValidateResponseDTO> response=authClient.validate(token,id);
        if(!response.getStatusCode().equals(HttpStatus.ACCEPTED))
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        List<Role> roles=response.getBody().getUserResponse().getRoles();
        boolean isAdmin=false;
        for(Role i:roles){
            if(i.getRole().equals("ADMIN")){
                isAdmin=true;
                break;
            }
        }
        if(!isAdmin)
            return new ResponseEntity<>(null,HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProduct(@PathVariable long id) throws  ProductNotFound{
        ResponseEntity<Optional<ProductResponseDTO>> optionalResponseEntity=productService.getProductById(id);
        if(optionalResponseEntity.getBody().isEmpty())
            throw new ProductNotFound("product is not available");
        else{
            ResponseEntity<ProductResponseDTO>  response=new ResponseEntity<>(optionalResponseEntity.getBody().get(),optionalResponseEntity.getStatusCode());
            return response;
        }
    }

    @PostMapping()
    public ProductResponseDTO addNewProduct(@RequestBody ProductRequestDTO fakeStoreRequestProductDTO){
        return productService.createProduct(fakeStoreRequestProductDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable long id, @RequestBody ProductRequestDTO fakeStoreRequestProductDTO){
        return productService.updateProduct(id,fakeStoreRequestProductDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable long id){
        return productService.deleteProduct(id);
    }

    // exception advice class is better option

//    @ExceptionHandler(ProductNotFound.class)
//    public ResponseEntity<ErrorMessageDTO> exceptionHandler(Exception e){
//        ErrorMessageDTO errorMessageDTO=new ErrorMessageDTO();
//        errorMessageDTO.setMsg(e.getMessage());
//        return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);
//    }
}
