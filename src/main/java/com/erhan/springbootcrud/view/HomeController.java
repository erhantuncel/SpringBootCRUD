package com.erhan.springbootcrud.view;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.erhan.springbootcrud.model.Staff;
import com.erhan.springbootcrud.service.StaffService;

@Controller
public class HomeController {

	@Autowired
	StaffService staffService;
	
	@RequestMapping(value = "/")
	public ModelAndView getIndexPage(ModelAndView model) {
		String title = "Personel Bilgi Sistemi";
		
		List<Staff> staffList = staffService.findAll();
		if(staffList.size() <= 0) {
			staffList = null;
		}
		
		model.addObject("title", title);
		model.addObject("staffList", staffList);
		model.setViewName("index");
		return model;
	}
	
	@RequestMapping(value = "/{page}")
	public ModelAndView getPage(@PathVariable String page, ModelAndView model) {
		model.setViewName(page);
		return model;
	}
}
