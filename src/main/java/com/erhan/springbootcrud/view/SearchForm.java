package com.erhan.springbootcrud.view;

import javax.validation.constraints.NotEmpty;

import com.erhan.springbootcrud.model.SearchType;

public class SearchForm {

	@NotEmpty
	private String keyword;
	
	private SearchType searchType;

	public String getKeyword() {
		return keyword;
	}

	public SearchForm() {
	}
	
	public SearchForm(String keyword, SearchType searchType) {
		super();
		this.keyword = keyword;
		this.searchType = searchType;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public SearchType getSearchType() {
		return searchType;
	}

	public void setSearchType(SearchType searchType) {
		this.searchType = searchType;
	}

	@Override
	public String toString() {
		return "SearchForm [keyword=" + keyword + ", searchType=" + searchType + "]";
	}
	
}
