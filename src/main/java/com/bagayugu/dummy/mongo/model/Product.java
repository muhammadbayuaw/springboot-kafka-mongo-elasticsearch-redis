package com.bagayugu.dummy.mongo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Bayu Agusto
 *
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
public class Product {
	@Id
	private String id;
	private String name;
	private Integer quantity;
	private Integer price;
	private Date expiredDate;
}
