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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.erhan.springbootcrud.model.Staff;
import com.erhan.springbootcrud.service.StaffService;

@Controller
public class StaffController {

	private static final Logger logger = LoggerFactory.getLogger(StaffController.class);
	
	@Autowired
	StaffService staffService;
	
	@RequestMapping(value = "/addStaff", method = RequestMethod.GET)
	public ModelAndView showAddStaffForm(Staff staff, ModelAndView model) {  
		model.setViewName("/addStaff");
		return model;
	}

	@RequestMapping(value = "/addStaff", method = RequestMethod.POST)
	public ModelAndView processAddStaffForm(@Valid Staff staff, BindingResult result, 
											ModelAndView model, RedirectAttributes redir) {
		
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError error : allErrors) {
				logger.warn(error.toString());
			}
			model.setViewName("/addStaff");
		} else {
			staffService.create(staff);
			logger.info("Staff (" + staff.getFirstName() + " " + staff.getLastName() + ") is added successfully.");
			String msg = "Personel (" + staff.getFirstName() + " " + staff.getLastName() + ") eklendi.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", msg);
			model.setViewName("redirect:" + "/");
		}
		return model;
	}
	
	@RequestMapping(value = "/updateStaff/{id}", method = RequestMethod.GET)
	public ModelAndView showUpdateStaffForm(@PathVariable("id") Short id, ModelAndView model) {
		Staff staffToUpdate = staffService.findById(id);
		model.addObject("staffToUpdate", staffToUpdate);
		model.setViewName("/updateStaff");
		return model;
	}
	
	@RequestMapping(value = "/updateStaff", method = RequestMethod.POST)
	public ModelAndView processShowStaffUpdateForm(@Valid @ModelAttribute("staffToUpdate") Staff staff, BindingResult result, 
													ModelAndView model, RedirectAttributes redir) {
		if(result.hasErrors()) {
			List<ObjectError> allErrors = result.getAllErrors();
			for(ObjectError error : allErrors) {
				logger.warn(error.toString());
			}
		} else {
			staffService.update(staff);
			String msg = "Personel (" + staff.getFirstName() + " " + staff.getLastName() + ") güncellendi.";
			redir.addFlashAttribute("warningType", "info");
			redir.addFlashAttribute("msg", msg);
			model.setViewName("redirect:" + "/");
		}
		return model;
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancelFormProcess(ModelAndView model) {
		model.setViewName("/");
		return model;
	}
}
