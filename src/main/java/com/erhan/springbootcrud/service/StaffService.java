package com.erhan.springbootcrud.service;

import java.util.Date;
import java.util.List;

import com.erhan.springbootcrud.model.Staff;

public interface StaffService {
	
	public void create(Staff staff);
	public List<Staff> findAll();
	public Staff findById(Short id);
	public void delete(Staff staff);
	public void update(Staff staff);
	public List<Staff> findByFirstName(String firstName);
	public List<Staff> findByLastName(String lastName);
	public List<Staff> findByPhone(String phone);
	public List<Staff> findByEmail(String email);
	public List<Staff> findByCreateDate(Date createDate);
}
