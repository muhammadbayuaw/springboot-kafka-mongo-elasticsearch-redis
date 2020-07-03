package com.bagayugu.dummy.request;

import java.util.Date;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

import lombok.Data;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Data
public class ProductRequest {
	@NotBlank
	private String name;
	@PositiveOrZero
	private Integer quantity;
	@PositiveOrZero
	private Integer price;
	@FutureOrPresent
	private Date expiredDate;
}
