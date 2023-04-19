package com.project.dummy.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Elasticsearch product model. In this case we only need to get name and price data.
 * @author Muhammad Bayu Agusto
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "dummy.products")
public class EsProduct {
	@Id
	private String id;
	private String name;
	private Integer price;
}
