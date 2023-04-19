package com.project.dummy.message;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.redis.core.RedisHash;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "Product", timeToLive = 60)
public class ProductDto implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private Integer quantity;
	private Integer price;
	private Date expiredDate;
}
