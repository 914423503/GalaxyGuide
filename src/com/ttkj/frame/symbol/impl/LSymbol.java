package com.ttkj.frame.symbol.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.Utils;
import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;
/**
 * LÂÞÂí·ûºÅ
 * 
 * @author fengtao
 */
public class LSymbol extends Symbol {

	@Override
	public Integer initDeep() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public List<SymbolCode> initBeforSymbol() {
		// TODO Auto-generated method stub
		ArrayList<SymbolCode> beforSymbol = new ArrayList<SymbolCode>();
		beforSymbol.add(SymbolCode.I);
		beforSymbol.add(SymbolCode.V);
		beforSymbol.add(SymbolCode.X);
		return beforSymbol;
	}

	@Override
	public SymbolCode initSymbolCode() {
		// TODO Auto-generated method stub
		return SymbolCode.L;
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
