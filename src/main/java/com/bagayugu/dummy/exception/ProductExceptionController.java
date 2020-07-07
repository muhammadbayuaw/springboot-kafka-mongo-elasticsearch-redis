package com.bagayugu.dummy.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bagayugu.dummy.response.BaseResponse;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ProductExceptionController {
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<BaseResponse> notFoundException(ProductNotFoundException ex){
		return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), ex.getCode()), 
				HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
		//Bean validation handling
		return new ResponseEntity<BaseResponse>(new BaseResponse(ex.getMessage(), 
				String.valueOf(HttpStatus.BAD_REQUEST.value())), HttpStatus.BAD_REQUEST);
	}
	
}
