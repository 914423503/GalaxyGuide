package com.ttkj.frame.symbol;

import com.ttkj.frame.Utils;

public enum SymbolCode {
	I("I",1),V("V",5),X("X",10),L("L",50),C("C",100),D("D",500),M("M",1000);
	private Integer value;
	private String code;
	private SymbolCode(String code,Integer value) {
		this.value=value;
		this.code = code;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public static SymbolCode getValueByCode(String code){
		SymbolCode mycode = null;
		SymbolCode[] values = values();
		for (SymbolCode symbolCode : values) {
			if(symbolCode.code.equals(code)){
				mycode = symbolCode;
				break;
			}
		}
		return mycode;
	}
	
	
	public static int getValueByCodeValue(String code){
		SymbolCode mycode = getValueByCode(code);
		if(mycode == null){
			return Utils.DEF_CODE;
		}
		return mycode.value;
	}
	
}
