package com.bagayugu.dummy.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MailDto {
	private String toUsername;
	private String fromUsername;
	private String fromPassword;
	private String subject;
	private String message;
}
