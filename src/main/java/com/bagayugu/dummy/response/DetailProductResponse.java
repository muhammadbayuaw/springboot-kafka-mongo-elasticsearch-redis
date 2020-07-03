package com.bagayugu.dummy.response;

import com.bagayugu.dummy.message.ProductDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class DetailProductResponse extends BaseResponse{
	private ProductDto product;
	
	public DetailProductResponse(String message, String code) {
		super(message, code);
	}

	public DetailProductResponse(ProductDto product, String message, String code) {
		super(message, code);
		this.product = product;
	}
	
}
