package com.kong.quicksearch;

public class Friend implements Comparable<Friend>{
	private String name;
	private String pinyin;

	public Friend(String name) {
		super();
		this.name = name;
		
		setPinyin(PinYinUtil.getPinyin(name));
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int compareTo(Friend another) {
		return getPinyin().compareTo(another.getPinyin());
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	
	
}
