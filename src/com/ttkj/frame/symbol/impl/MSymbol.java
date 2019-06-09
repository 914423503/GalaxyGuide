package com.ttkj.frame.symbol.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;
/**
 * MÂÞÂí·ûºÅ
 * 
 * @author fengtao
 */
public class MSymbol extends Symbol {

	@Override
	public Integer initDeep() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public List<SymbolCode> initBeforSymbol() {
		// TODO Auto-generated method stub
		ArrayList<SymbolCode> beforSymbol = new ArrayList<SymbolCode>();
		return beforSymbol;
	}

	@Override
	public SymbolCode initSymbolCode() {
		// TODO Auto-generated method stub
		return SymbolCode.M;
	}

	@Override
	public int initMaxCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public int initAppearNum() {
		// TODO Auto-generated method stub
		return 4;
	}
	@Override
	public boolean checkOrder(String code) {
		// TODO Auto-generated method stub
		return true;
	}
}
