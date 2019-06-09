package com.ttkj.frame.output;

import java.util.HashMap;
import java.util.List;

import com.ttkj.frame.Utils;
import com.ttkj.frame.po.Sentence;
import com.ttkj.frame.symbol.Symbol;
import com.ttkj.frame.symbol.SymbolCode;

/**
 * �����������������̨
 * 
 * @author fengtao
 */
public class OutputManger {

	private List<Symbol> symbols;

	public OutputManger(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	/**
	 * ����ҵ���ӡ���������̨
	 */
	public void outputConsole(HashMap<String, String> metalMap,List<Sentence> sentences) {
		for (Sentence sentence : sentences) {
			try {
				// ������ how many ��ͷ�����
				if (sentence.getSubject().startsWith(Utils.DEF_HOW_MANY)) {
					String myGuest = sentence.getGuest().replace("?", "").trim();
					String[] guestArray = myGuest.split(" ");
					StringBuffer metal = new StringBuffer();
					for (String string : guestArray) {
						if (!Utils.isEmpty(string.trim())) {
							if (!Utils.isEmpty(SymbolCode.getValueByCode(metalMap.get(string.toString())))) {
								if (Utils.isEmpty(metalMap.get(string.toString().trim()))) {
									throw new Exception(Utils.DEF_RECKON_ERROR);
								}
								metal.append(metalMap.get(string.toString()).trim());
							} else {
								String credits = metalMap.get(string.toString());
								if (Utils.isEmpty(credits)) {
									throw new Exception(Utils.DEF_RECKON_ERROR);
								}
								if(!checkReckon(metal.toString().trim())){
									System.out.println(Utils.DEF_RECKON_ERROR);
									continue;
								}
								String mycredits = String
										.valueOf(Double.parseDouble(credits) * Utils.reckon(metal.toString().trim()));
								System.out.println(myGuest + Utils.DEF_PREDICATE
										+ (mycredits.endsWith(".0") ? mycredits.replace(".0", " ") : " ")
										+ Utils.DEF_CREDITS_NAME);
							}
						}
					}
					// ������ how muth ��ͷ�����
				} else if (sentence.getSubject().startsWith(Utils.DEF_HOW_MUCH)) {
					String myGuest = sentence.getGuest().replace("?", "").trim();
					String[] guestArray = myGuest.split(" ");
					StringBuffer metal = new StringBuffer();
					for (String string : guestArray) {
						if (!Utils.isEmpty(string.trim())) {
							if (Utils.isEmpty(metalMap.get(string.toString().trim()))) {
								throw new Exception(Utils.DEF_RECKON_ERROR);
							}
							metal.append(metalMap.get(string.toString().trim()));
						}
					}
					if(!checkReckon(metal.toString().trim())){
						System.out.println(Utils.DEF_RECKON_ERROR);
						continue;
					}
					System.out.println(myGuest + Utils.DEF_PREDICATE + Utils.reckon(metal.toString().trim()));
				}
			} catch (Exception e) {
				System.out.println(Utils.DEF_RECKON_ERROR);
			}

		}
	}

	private boolean checkReckon(String code) {
		boolean checkSuccess = true;
		for (Symbol symbol : symbols) {
			if(!checkSuccess){
				break;
			}
			try {
				symbol.checkCode(code);
			} catch (Exception e) {
				e.printStackTrace();
				checkSuccess = false;
			}
			
		}
		return checkSuccess;
	}

}
