package com.metazz.metazzspace.common.exception.utils;
import com.metazz.metazzspace.common.exception.BaseException;
import com.metazz.metazzspace.common.exception.enums.NumberTypeEnum;

public interface Assert {

    BaseException newException();

    default void assertNotNull(Object obj) throws BaseException {
        if(null == obj) {
            throw newException();
        }
    }

    default void assertNotEquals(String str1,String str2) throws BaseException {
        if(!str1.equals(str2)) {
            throw newException();
        }
    }

    default void assertNotEquals(Number num1,Number num2) throws BaseException {
        int lastDotIndex = num1.getClass().getTypeName().lastIndexOf(".")+1;
        boolean isEquals = NumberTypeEnum.valueOf(num1.getClass().getTypeName().substring(lastDotIndex).toUpperCase()).isEquals(num1, num2);
        if(!isEquals) {
            throw newException();
        }
    }

}
