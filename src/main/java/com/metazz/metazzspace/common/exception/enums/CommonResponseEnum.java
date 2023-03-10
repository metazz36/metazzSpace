package com.metazz.metazzspace.common.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonResponseEnum implements BusinessExceptionAssert {

	SERVER_ERROR(9999,"Server Inner Error");
	
	private Integer code;
	private String message;

}
