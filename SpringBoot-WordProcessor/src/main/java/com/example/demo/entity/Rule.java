package com.example.demo.entity;

/**
 * Define filter rule
 * @author qiao
 *
 */
public class Rule {
	private String startStr;
	private String endStr;
	private Integer maxLength;
	private Integer minLength;
	
	public String getStartStr() {
		return startStr;
	}
	public void setStartStr(String startStr) {
		this.startStr = startStr;
	}
	public String getEndStr() {
		return endStr;
	}
	public void setEndStr(String endStr) {
		this.endStr = endStr;
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		if(!startStr.isEmpty()) {
			sb.append(" start with ").append(startStr);
		}
		if(!endStr.isEmpty()) {
			sb.append(" end with ").append(endStr);
		}
		if(maxLength != null) {
			sb.append(" shorter than ").append(maxLength);
		}
		if(minLength != null) {
			sb.append(" longer than ").append(minLength);
		}
		return sb.toString();
	}

}
