package com.vijay.filehandler.springbootfilehandler.model;

public class UserData {

	private String text;
	private boolean header;
	private String fontName;
	private int fontType;
	private int fontWeight;
	
	
	public UserData() {
		
	}
		
	public UserData(String text, boolean header, String fontName, int fontType, int fontWeight) {
		super();
		this.text = text;
		this.header = header;
		this.fontName = fontName;
		this.fontType = fontType;
		this.fontWeight = fontWeight;
	}


	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public boolean isHeader() {
		return header;
	}
	
	public void setHeader(boolean header) {
		this.header = header;
	}
	
	public String getFontName() {
		return fontName;
	}
	
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
	
	public int getFontType() {
		return fontType;
	}
	
	public void setFontType(int fontType) {
		this.fontType = fontType;
	}
	
	public int getFontWeight() {
		return fontWeight;
	}
	
	public void setFontWeight(int fontWeight) {
		this.fontWeight = fontWeight;
	}

	@Override
	public String toString() {
		return "UserData [text=" + text + ", header=" + header + ", fontName=" + fontName + ", fontType=" + fontType
				+ ", fontWeight=" + fontWeight + "]";
	}
	
}
