package com.project.dummy.controller;

import com.project.dummy.config.SwaggerConfig;
import com.project.dummy.elasticsearch.model.EsProduct;
import com.project.dummy.elasticsearch.repository.EsProductRepository;
import com.project.dummy.response.ListProductResponse;
import com.project.dummy.util.ResponseUtil;
import com.project.dummy.util.UrlUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = UrlUtil.V1.PRODUCTS)
@Api(tags = { SwaggerConfig.ELASTIC_CONTROLLER })
public class ElasticProductController {

	@Autowired
	private EsProductRepository repository;
	
	@ApiOperation(value = "Return list of products via Elasticsearch")
	@GetMapping
	public ResponseEntity<ListProductResponse> findAllViaElastic(){
		List<EsProduct> products = repository.findAll().getContent();
		
		return new ResponseEntity<>(
				new ListProductResponse(products, ResponseUtil.Message.SUCCESS, ResponseUtil.Code.SUCCESS),
				HttpStatus.OK
			);
	}
	
}
