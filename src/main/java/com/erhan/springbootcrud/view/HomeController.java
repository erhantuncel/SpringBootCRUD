package com.erhan.springbootcrud.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value = "/")
	public ModelAndView getIndexPage(ModelAndView model) {
		String title = "Personel Bilgi Sistemi";
		model.addObject("title", title);
		model.setViewName("index");
		return model;
	}
}
