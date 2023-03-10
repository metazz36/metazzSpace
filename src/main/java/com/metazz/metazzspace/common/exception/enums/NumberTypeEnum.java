package com.metazz.metazzspace.common.exception.enums;

import cn.hutool.core.util.NumberUtil;

public enum NumberTypeEnum {

	INTEGER {
		@Override
		public boolean isEquals(Number num1, Number num2) {
			return NumberUtil.compare((Integer)num1, (Integer)num2)==0;
		}
	},
	LONG {
		@Override
		public boolean isEquals(Number num1, Number num2) {
			return NumberUtil.compare((Long)num1, (Long)num2)==0;
		}
	},
	FLOAT {
		@Override
		public boolean isEquals(Number num1, Number num2) {
			return NumberUtil.compare((Float)num1, (Float)num2)==0;
		}
	},
	DOUBLE {
		@Override
		public boolean isEquals(Number num1, Number num2) {
			return NumberUtil.compare((Double)num1, (Double)num2)==0;
		}
	};
	
	public abstract boolean isEquals(Number num1,Number num2);

}
