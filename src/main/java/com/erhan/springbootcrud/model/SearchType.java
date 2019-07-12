package com.erhan.springbootcrud.model;

public enum SearchType {
	BY_FIRST_NAME("Ad"),
	BY_LAST_NAME("Soyad"),
	BY_PHONE("Telefon"),
	BY_EMAIL("Email");
	
	private final String displayName;

	private SearchType(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}
}