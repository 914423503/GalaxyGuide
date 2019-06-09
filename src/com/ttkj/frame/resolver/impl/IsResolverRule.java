package com.ttkj.frame.resolver.impl;

import java.util.ArrayList;
import java.util.List;

import com.ttkj.frame.Utils;
import com.ttkj.frame.po.Sentence;
import com.ttkj.frame.resolver.ResolverRule;
/**
 * 以 "is" 为关键子的解析接口
 * @author fengtao
 */
public class IsResolverRule implements ResolverRule {

	@Override
	public List<Sentence> resilver(List<String> codes) {
		List<Sentence> sentenceList = new ArrayList<Sentence>();
		Sentence sentence = null;
		for (String code : codes) {
			sentence = new Sentence();
			String[] senItem = code.trim().split(Utils.DEF_PREDICATE);
			if (senItem != null && senItem.length > 0) {
				sentence.setValue(code);
				sentence.setSubject(senItem[0].trim());
				sentence.setPredicate(Utils.DEF_PREDICATE);
				if (senItem.length > 1) {
					sentence.setGuest(senItem[1].trim());
				}
				sentenceList.add(sentence);
			}
		}
		return sentenceList;
	}

}
