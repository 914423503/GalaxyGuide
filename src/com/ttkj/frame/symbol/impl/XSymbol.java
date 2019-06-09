package com.ttkj.frame.symbol.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;
/**
 * XÂÞÂí·ûºÅ
 * 
 * @author fengtao
 */
public class XSymbol extends Symbol {

	@Override
	public Integer initDeep() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public List<SymbolCode> initBeforSymbol() {
		// TODO Auto-generated method stub
		ArrayList<SymbolCode> beforSymbol = new ArrayList<SymbolCode>();
		beforSymbol.add(SymbolCode.L);
		beforSymbol.add(SymbolCode.C);
		return beforSymbol;
	}

	@Override
	public SymbolCode initSymbolCode() {
		// TODO Auto-generated method stub
		return SymbolCode.X;
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
