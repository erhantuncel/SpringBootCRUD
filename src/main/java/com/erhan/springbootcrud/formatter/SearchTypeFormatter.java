package com.erhan.springbootcrud.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.format.Formatter;

import com.erhan.springbootcrud.model.SearchType;

public class SearchTypeFormatter implements Formatter<SearchType> {

	@Resource
	MessageSource messageSource;
	
	@Override
	public String print(SearchType object, Locale locale) {
		String code = "index.content.search.type." + object.name().toLowerCase(Locale.ENGLISH);
		return messageSource.getMessage(code, null, locale);
	}

	@Override
	public SearchType parse(String text, Locale locale) throws ParseException {
		return SearchType.parse(text);
	}

}
