package com.ttkj.frame.input;

import java.util.HashMap;
import java.util.List;

import com.ttkj.frame.Utils;
import com.ttkj.frame.po.Sentence;
import com.ttkj.frame.resolver.ResolverRule;
import com.ttkj.frame.resolver.impl.IsResolverRule;
import com.ttkj.frame.symbol.Symbol;
/**
 * 输入管理器
 * 用于解析关键字并存放到Map中
 * @author fengtao
 */
public class InputManager {
	private ResolverRule resolverRule = new IsResolverRule();
	private List<String> fileValues = Utils.readInInputFile();
	private HashMap<String, String> metalMap = new HashMap<String, String>();
	private List<Sentence> sentences = null;
	private List<Symbol> symbols;
	public InputManager(List<Symbol> symbols) {
		this.symbols = symbols;
		sentences = resolverRule.resilver(fileValues);
	}


	public void resolverMetal() {
		String[] subjectArray = null;
		StringBuffer creditTitle = null;
		for (Sentence sentence : sentences) {
			creditTitle = new StringBuffer();
			// 既不是以how many开始也不是以how much开始
			if (!sentence.getSubject().startsWith(Utils.DEF_HOW_MANY)
					&& !sentence.getSubject().startsWith(Utils.DEF_HOW_MUCH)) {
				subjectArray = sentence.getSubject().split(" ");
				for (int i = 0; i < subjectArray.length; i++) {
					if (i != subjectArray.length - 1) {
						creditTitle.append(metalMap.get(subjectArray[i]).trim());
					} else {
						if (creditTitle.toString().trim().length() > 0) {
							String myGuest = sentence.getGuest().replace(Utils.DEF_CREDITS_NAME, "").trim();
							if(!checkReckon(creditTitle.toString().trim())){
								continue;
							}
							int title = Utils.reckon(creditTitle.toString().trim());
							metalMap.put(subjectArray[i], String.valueOf(Double.parseDouble(myGuest) / title));
						} else {
							metalMap.put(subjectArray[i], sentence.getGuest().trim());
						}
					}
				}
			}
		}
	}

	public List<String> getFileValues() {
		return fileValues;
	}

	public void setFileValues(List<String> fileValues) {
		this.fileValues = fileValues;
	}

	public HashMap<String, String> getMetalMap() {
		return metalMap;
	}

	public void setMetalMap(HashMap<String, String> metalMap) {
		this.metalMap = metalMap;
	}

	public List<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(List<Sentence> sentences) {
		this.sentences = sentences;
	}
	/**
	 * 检测code是否合法
	 * @param code
	 * @return
	 */
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
