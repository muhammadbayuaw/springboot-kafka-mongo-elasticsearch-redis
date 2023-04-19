package com.project.dummy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Muhammad Bayu Agusto
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
	private String message;
	private String code;
}
