package com.erhan.springbootcrud.model;

import java.util.Arrays;
import java.util.List;

public enum SearchType {
	BY_FIRST_NAME,
	BY_LAST_NAME,
	BY_PHONE,
	BY_EMAIL;
	
	public static List<SearchType> list() {
		return Arrays.asList(values());
	}
	
	public static SearchType parse(String value) {
		SearchType searchType = null;
		for(SearchType typeForTest : list()) {
			if(typeForTest.name().equalsIgnoreCase(value)) {
				searchType = typeForTest;
				break;
			}
		}
		return searchType;
	}
}