package com.erhan.springbootcrud.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erhan.springbootcrud.dao.StaffDAO;
import com.erhan.springbootcrud.model.Staff;

@Service
@Transactional
public class StaffService {

	@Autowired
	StaffDAO staffDAO;
	
	public void create(Staff staff) {
		staffDAO.save(staff);
	}
	
	public List<Staff> findAll() {
		return staffDAO.findAll();
	}
	
	public Staff findById(Short id) {
		return staffDAO.findById(id).orElse(null);
	}
	
	public void delete(Staff staff) {
		staffDAO.delete(staff);
	}
	
	public List<Staff> findByFirstName(String firstName) {
		return staffDAO.findByFirstName(firstName);
	}
	
	public List<Staff> findByLastName(String lastName) {
		return staffDAO.findByLastName(lastName);
	}
	
	public List<Staff> findByPhone(String phone) {
		return staffDAO.findByPhone(phone);
	}
	
	public List<Staff> findByEmail(String email) {
		return staffDAO.findByEmail(email);
	}
	
	public List<Staff> findByCreateDate(Date createDate) {
		return staffDAO.findByCreateDate(createDate);
	}
	
}
