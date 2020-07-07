package com.bagayugu.dummy.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Muhammad Bayu Agusto
 *
 */

@Setter
@Getter
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	private String message;
	private String code;
	
	public ProductNotFoundException(String message) {
		super();
		this.message = message;
		this.code = String.valueOf(HttpStatus.NOT_FOUND.value());
	}
	
	public ProductNotFoundException(String message, String code) {
		super();
		this.message = message;
		this.code = code;
	}
}
