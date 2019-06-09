package com.ttkj.frame.symbol;

import java.util.List;

import com.ttkj.frame.Utils;

/**
 * 罗马符号
 * 
 * @author fengtao
 */
public abstract class Symbol {
	/**
	 * 值
	 */
	Integer value = 0;
	/**
	 * 符号
	 * 
	 * @param deep
	 */
	protected String symbol = null;

	/**
	 * 代表级别
	 * 
	 * @param deep
	 */
	int deep = -1;

	public abstract Integer initDeep();

	/**
	 * 符号编码
	 */
	SymbolCode symbolCode = null;

	public abstract SymbolCode initSymbolCode();

	/**
	 * 符号可以在那些符号之前
	 */
	List<SymbolCode> beforSymbol = null;

	public abstract List<SymbolCode> initBeforSymbol();

	/**
	 * 最多出現次數 Utils.DEF_CODE 表示无效
	 */
	int maxCount = Utils.DEF_CODE;

	public abstract int initMaxCount();

	/**
	 * 连续出现个数 Utils.DEF_CODE 表示无效
	 */
	int appearNum = Utils.DEF_CODE;

	public abstract int initAppearNum();

	/**
	 * 初始化
	 */
	private void init() {
		this.symbolCode = initSymbolCode();
		this.symbol = symbolCode.getCode();
		this.value = symbolCode.getValue();
		this.deep = initDeep();
		this.maxCount = initMaxCount();
		this.appearNum = initAppearNum();
		this.beforSymbol = initBeforSymbol();
	}

	/**
	 * 构造器
	 */
	public Symbol() {
		this.init();
	}

	@Override
	public String toString() {
		return "Symbol [value=" + value + ", deep=" + deep + ", symbol=" + symbol + "]";
	}

	/**
	 * 校验数据是否合法
	 * 
	 * @param code
	 * @return
	 */
	public void checkCode(String code) {
		if (maxCount != Utils.DEF_CODE) {
			// 断言 "数据"中包含的 "符号" 不超过 "最多出現次數"
			// assert Utils.countString(code, this.symbol)<= maxCount:"最多出现次数校验失败";
			if (Utils.countString(code, this.symbol) > maxCount) {
				throw new RuntimeException("最多出现次数校验失败 code:" + this.symbol);
			}
		}

		if (appearNum != Utils.DEF_CODE) {
			StringBuffer appearStr = new StringBuffer();
			for (int i = 0; i <= appearNum; i++) {
				appearStr.append(this.symbol);
			}
			// 断言 "数据"中包含的 "符号" 连续不超过 "连续出现个数"
			// assert !code.contains(appearStr.toString()):"连续出现个数校验失败";
			if (code.contains(appearStr.toString())) {
				throw new RuntimeException("连续出现个数校验失败 code:" + this.symbol);
			}
		}
		// 去除重复
		// code = Utils.reckon3ByCode(code);
		boolean cheOrderSuc = checkOrder(code);
		if (!cheOrderSuc) {
			StringBuffer msg = new StringBuffer(this.symbol).append(" can be subtracted from ");
			if (this.symbol == SymbolCode.V.getCode() || this.symbol == SymbolCode.L.getCode()
					|| this.symbol == SymbolCode.D.getCode()) {
				msg = new StringBuffer("V L and D can never be subtracted");
			} else {
				for (int i = 0; i < beforSymbol.size(); i++) {
					msg.append(",").append(beforSymbol.get(i).getCode());
				}
			}

			throw new RuntimeException(msg.toString().replaceFirst(",", ""));
		}
	}

	public boolean checkOrder(String code) {
		// TODO Auto-generated method stub
		// "I" can be subtracted from "V" and "X" only.
		// "X" can be subtracted from "L" and "C" only.
		// "C" can be subtracted from "D" and "M" only.
		// "V", "L", and "D" can never be subtracted. //只能被+
		boolean isSuccess = false;
		// 去除重复
		code = Utils.reckon3ByCode(code);
		char[] codeArray = code.toCharArray();
		// 当前下标
		int index = 0;
		// 每前一個數
		char start = '0';
		// 每后一個數
		char end = '0';
		boolean isEnter = false;
		for (int i = codeArray.length - 1; i >= 0; i--) {
			if (((index = index + 1) % 2) == 1) {
				start = codeArray[i];
			} else {
				end = codeArray[i];
				if(this.symbol.equals(String.valueOf(end))) {
					isEnter = true;
					for (SymbolCode symbol : beforSymbol) {
						if(symbol.getCode().equals(String.valueOf(start)) || this.symbol.equals(String.valueOf(start))) {
							isSuccess = true;
							break;
						}
					}
				}
			}
		}
		if(!isEnter) {
			isSuccess = true;
		}
		// 拼接无效字符"!",防止以 this.symbol 开头
		// String[] orderArrays = ("!" + code).split(this.symbol);

		// if (beforSymbol.size() > 0 && orderArrays.length > 1) {
		// // 从第二个开始
		// for (int i = 1; i < orderArrays.length && !isSuccess; i++) {
		// for (SymbolCode symbol : beforSymbol) {
		// if (orderArrays[i].startsWith(symbol.getCode()) ||
		// orderArrays[i].startsWith(this.symbol)) {
		// isSuccess = true;
		// }
		// }
		// }
		// } else {
		// isSuccess = true;
		// }
		return isSuccess;
	}
}
