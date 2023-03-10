package com.metazz.metazzspace.common.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ArgumentResponseEnum implements BusinessExceptionAssert {

	VALID_ERROR(5001,"");
	
	private Integer code;
	
	private String message;

}
