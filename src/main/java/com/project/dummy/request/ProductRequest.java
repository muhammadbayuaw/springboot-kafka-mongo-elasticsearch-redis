package com.project.dummy.request;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

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
public class ProductRequest {
	@NotBlank
	private String name;
	@PositiveOrZero
	private Integer quantity;
	@PositiveOrZero
	private Integer price;
	@NotNull
	private Date expiredDate;
}
