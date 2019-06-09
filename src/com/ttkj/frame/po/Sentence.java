package com.ttkj.frame.po;
/**
 * Óï¾äÀà
 * @author Administrator
 */
public class Sentence {
	/**
	 * Ö÷Óï
	 */
	private String subject;
	/**
	 * Î½Óï
	 */
	private String predicate;
	/**
	 * ±öÓï
	 */
	private String guest;
	/**
	 * Õû¸öÓï¾ä
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
