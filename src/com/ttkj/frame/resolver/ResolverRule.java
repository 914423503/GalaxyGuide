package com.ttkj.frame.resolver;

import java.util.List;

import com.ttkj.frame.po.Sentence;
/**
 * 語句解析接口
 * @author fengtao
 */
public interface ResolverRule {
	/**
	 * 解析 所有语句转换成语句实体
	 * @param codes
	 * @return
	 */
	public List<Sentence> resilver(List<String> codes);

}
