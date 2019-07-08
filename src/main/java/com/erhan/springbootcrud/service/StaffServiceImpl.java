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
public class StaffServiceImpl implements StaffService {

	@Autowired
	StaffDAO staffDAO;
	
	@Override
	public void create(Staff staff) {
		staffDAO.save(staff);
	}
	
	@Override
	public List<Staff> findAll() {
		return staffDAO.findAll();
	}
	
	@Override
	public Staff findById(Short id) {
		return staffDAO.findById(id).orElse(null);
	}
	
	@Override
	public void delete(Staff staff) {
		staffDAO.delete(staff);
	}
	
	@Override
	public List<Staff> findByFirstName(String firstName) {
		return staffDAO.findByFirstName(firstName);
	}
	
	@Override
	public List<Staff> findByLastName(String lastName) {
		return staffDAO.findByLastName(lastName);
	}
	
	@Override
	public List<Staff> findByPhone(String phone) {
		return staffDAO.findByPhone(phone);
	}
	
	@Override
	public List<Staff> findByEmail(String email) {
		return staffDAO.findByEmail(email);
	}
	
	@Override
	public List<Staff> findByCreateDate(Date createDate) {
		return staffDAO.findByCreateDate(createDate);
	}
	
}
