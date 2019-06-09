package com.ttkj.frame.symbol.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.Utils;
import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;
/**
 * VÂÞÂí·ûºÅ
 * 
 * @author fengtao
 */
public class VSymbol extends Symbol {

	@Override
	public Integer initDeep() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public List<SymbolCode> initBeforSymbol() {
		// TODO Auto-generated method stub
		ArrayList<SymbolCode> beforSymbol = new ArrayList<SymbolCode>();
		beforSymbol.add(SymbolCode.I);
		return beforSymbol;
	}

	@Override
	public SymbolCode initSymbolCode() {
		// TODO Auto-generated method stub
		return SymbolCode.V;
	}

	@Override
	public int initMaxCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public int initAppearNum() {
		// TODO Auto-generated method stub
		return Utils.DEF_CODE;
	}

}
