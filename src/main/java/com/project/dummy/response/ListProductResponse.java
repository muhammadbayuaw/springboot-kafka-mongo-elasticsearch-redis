package com.project.dummy.response;

import java.util.List;

import com.project.dummy.elasticsearch.model.EsProduct;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ListProductResponse extends BaseResponse{
	private List<EsProduct> products;
	
	public ListProductResponse(String message, String code) {
		super(message, code);
	}
	
	public ListProductResponse(List<EsProduct> products, String message, String code) {
		super(message, code);
		this.products = products;
	}
}
