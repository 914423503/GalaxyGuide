package com.ttkj.frame.resolver;

import java.util.List;

import com.ttkj.frame.po.Sentence;
/**
 * �Z������ӿ�
 * @author fengtao
 */
public interface ResolverRule {
	/**
	 * ���� �������ת�������ʵ��
	 * @param codes
	 * @return
	 */
	public List<Sentence> resilver(List<String> codes);

}
