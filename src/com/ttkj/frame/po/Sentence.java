package com.ttkj.frame.po;
/**
 * �����
 * @author Administrator
 */
public class Sentence {
	/**
	 * ����
	 */
	private String subject;
	/**
	 * ν��
	 */
	private String predicate;
	/**
	 * ����
	 */
	private String guest;
	/**
	 * �������
	 */
	private String value;
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getPredicate() {
		return predicate;
	}
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
	public String getGuest() {
		return guest;
	}
	public void setGuest(String guest) {
		this.guest = guest;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return this.value;
	}
	
}
