package com.bagayugu.dummy.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

/**
 * Elasticsearch product model. In this case we only need to get name and price data.
 * @author Muhammad Bayu Agusto
 */
@Data
@Document(indexName = "dummy.products")
public class EsProduct {
	@Id
	private String id;
	private String name;
	private Integer price;
}
