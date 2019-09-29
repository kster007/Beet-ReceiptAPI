package com.beet.receipt.interfaces.utils;

import java.util.function.Function;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.beet.receipt.exception.ReceiptException;
import com.beet.receipt.interfaces.dto.ErrorDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ManageResponse<T, U> {
  
  private static final String DEFAULT_ERROR_MESSAGE = "An unknown error occurs please";
	
  private HttpStatus status;
 
  private Function<T,U> f;
  
  public ManageResponse(HttpStatus status, Function<T,U> f ) {
    this.status = status;
    this.f = f;
  }
  
  public ResponseEntity<Object> build(T argument){
    try {
      U response = f.apply(argument);
      return new ResponseEntity<>(response, status);
    }catch(ReceiptException e) {
      log.error(e.getClass().getName(), e);
      return new ResponseEntity<>(new ErrorDTO(e.getMessage()), e.getStatusCode() );
    }catch(Exception e) {
      log.error("EXCEPTION", e);
      return new ResponseEntity<>(new ErrorDTO(DEFAULT_ERROR_MESSAGE), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
