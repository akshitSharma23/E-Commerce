package com.example.ProductCatalogService.Controllers;


import com.example.ProductCatalogService.DTOs.ExceptionDTO.ErrorMessageDTO;
import com.example.ProductCatalogService.Exceptions.ProductNotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


// these method is for all controller globally
@ControllerAdvice
public class ExceptionAdvices {
    @ExceptionHandler(ProductNotFound.class)
//    public ResponseEntity<ErrorMessageDTO> exceptionHandler(Exception e){
    public ResponseEntity<String> exceptionHandler(Exception e){
//        ErrorMessageDTO errorMessageDTO=new ErrorMessageDTO();
//        errorMessageDTO.setMsg(e.getMessage());
//        return new ResponseEntity<>(errorMessageDTO, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>("Phat gya",HttpStatus.NOT_FOUND);
    }
}
