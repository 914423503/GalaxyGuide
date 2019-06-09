package com.ttkj.frame.symbol;

import java.util.List;

import com.ttkj.frame.Utils;

/**
 * �������
 * 
 * @author fengtao
 */
public abstract class Symbol {
	/**
	 * ֵ
	 */
	Integer value = 0;
	/**
	 * ����
	 * 
	 * @param deep
	 */
	protected String symbol = null;

	/**
	 * ������
	 * 
	 * @param deep
	 */
	int deep = -1;

	public abstract Integer initDeep();

	/**
	 * ���ű���
	 */
	SymbolCode symbolCode = null;

	public abstract SymbolCode initSymbolCode();

	/**
	 * ���ſ�������Щ����֮ǰ
	 */
	List<SymbolCode> beforSymbol = null;

	public abstract List<SymbolCode> initBeforSymbol();

	/**
	 * �����F�Δ� Utils.DEF_CODE ��ʾ��Ч
	 */
	int maxCount = Utils.DEF_CODE;

	public abstract int initMaxCount();

	/**
	 * �������ָ��� Utils.DEF_CODE ��ʾ��Ч
	 */
	int appearNum = Utils.DEF_CODE;

	public abstract int initAppearNum();

	/**
	 * ��ʼ��
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
	 * ������
	 */
	public Symbol() {
		this.init();
	}

	@Override
	public String toString() {
		return "Symbol [value=" + value + ", deep=" + deep + ", symbol=" + symbol + "]";
	}

	/**
	 * У�������Ƿ�Ϸ�
	 * 
	 * @param code
	 * @return
	 */
	public void checkCode(String code) {
		if (maxCount != Utils.DEF_CODE) {
			// ���� "����"�а����� "����" ������ "�����F�Δ�"
			// assert Utils.countString(code, this.symbol)<= maxCount:"�����ִ���У��ʧ��";
			if (Utils.countString(code, this.symbol) > maxCount) {
				throw new RuntimeException("�����ִ���У��ʧ�� code:" + this.symbol);
			}
		}

		if (appearNum != Utils.DEF_CODE) {
			StringBuffer appearStr = new StringBuffer();
			for (int i = 0; i <= appearNum; i++) {
				appearStr.append(this.symbol);
			}
			// ���� "����"�а����� "����" ���������� "�������ָ���"
			// assert !code.contains(appearStr.toString()):"�������ָ���У��ʧ��";
			if (code.contains(appearStr.toString())) {
				throw new RuntimeException("�������ָ���У��ʧ�� code:" + this.symbol);
			}
		}
		// ȥ���ظ�
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
		// "V", "L", and "D" can never be subtracted. //ֻ�ܱ�+
		boolean isSuccess = false;
		// ȥ���ظ�
		code = Utils.reckon3ByCode(code);
		char[] codeArray = code.toCharArray();
		// ��ǰ�±�
		int index = 0;
		// ÿǰһ����
		char start = '0';
		// ÿ��һ����
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
		// ƴ����Ч�ַ�"!",��ֹ�� this.symbol ��ͷ
		// String[] orderArrays = ("!" + code).split(this.symbol);

		// if (beforSymbol.size() > 0 && orderArrays.length > 1) {
		// // �ӵڶ�����ʼ
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
