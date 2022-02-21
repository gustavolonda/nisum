package com.iverno.gus.commonservice.endpoint.application.exception;

import com.iverno.gus.commonservice.endpoint.application.business.object.PhoneBO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseException extends Exception {
	private String status;
	private String message;
	private String module;
	private Exception exception;
}
