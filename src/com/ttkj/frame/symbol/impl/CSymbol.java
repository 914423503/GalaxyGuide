package com.ttkj.frame.symbol.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;
/**
 * CÂÞÂí·ûºÅ
 * 
 * @author fengtao
 */
public class CSymbol extends Symbol {

	@Override
	public Integer initDeep() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public List<SymbolCode> initBeforSymbol() {
		// TODO Auto-generated method stub
		ArrayList<SymbolCode> beforSymbol = new ArrayList<SymbolCode>();
		beforSymbol.add(SymbolCode.D);
		beforSymbol.add(SymbolCode.M);
		return beforSymbol;
	}

	@Override
	public SymbolCode initSymbolCode() {
		return SymbolCode.C;
	}

	@Override
	public int initMaxCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public int initAppearNum() {
		// TODO Auto-generated method stub
		return 3;
	}

}
