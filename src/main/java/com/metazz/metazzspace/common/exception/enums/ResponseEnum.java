package com.metazz.metazzspace.common.exception.enums;

import com.metazz.metazzspace.common.exception.utils.BusinessExceptionAssert;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseEnum implements BusinessExceptionAssert {
	
	BAD_LICENCE_TYPE(7001,"Bad licence type."),
	LICENCE_NOT_FOUND(7002,"Licence not found");
	
	private Integer code;
	private String message;

}
