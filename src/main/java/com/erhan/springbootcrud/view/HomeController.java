package com.erhan.springbootcrud.view;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.springbootcrud.model.SearchType;
import com.erhan.springbootcrud.model.Staff;
import com.erhan.springbootcrud.service.StaffService;

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	StaffService staffService;
	
	@RequestMapping(value = "/")
	public ModelAndView showIndexPage(ModelAndView model) {
		String title = "Personel Bilgi Sistemi - Personel Listesi";
		
		List<Staff> staffList = staffService.findAll();
		if(staffList.size() <= 0) {
			staffList = null;
		}
		
		model.addObject("title", title);
		model.addObject("staffList", staffList);
		model.addObject("searchForm", new SearchForm());
		model.addObject("searchTypes", SearchType.values());
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public ModelAndView processSearchStaffForm(@Valid @ModelAttribute("searchForm") SearchForm searchForm, 
										BindingResult result, ModelAndView model) {
		String title = "Personel Bilgi Sistemi - Personel Listesi";
		model.addObject("title", title);
		model.addObject("searchTypes", SearchType.values());
		model.setViewName("index");
		List<Staff> staffList = null;
		if(result.hasErrors()) {
			for(ObjectError error : result.getAllErrors()) {
				logger.warn(error.toString());
			}
			staffList = staffService.findAll();
		} else {
			switch (searchForm.getSearchType()) {
				case BY_FIRST_NAME:
					staffList = staffService.findByFirstName(searchForm.getKeyword());
					break;
				case BY_LAST_NAME:
					staffList = staffService.findByLastName(searchForm.getKeyword());
					break;
				case BY_PHONE:
					staffList = staffService.findByPhone(searchForm.getKeyword());
					break;
				case BY_EMAIL:
					staffList = staffService.findByEmail(searchForm.getKeyword());
					break;
			}
			if(staffList.size() <= 0) {
				staffList = null;
			}
			model.addObject("searchForm", new SearchForm());
		}
		model.addObject("staffList", staffList);
		return model;
	}	
}
