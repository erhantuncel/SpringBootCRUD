package com.erhan.springbootcrud.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erhan.springbootcrud.model.Staff;

@Repository
public interface StaffDAO extends JpaRepository<Staff, Short> {

	public List<Staff> findByFirstName(String firstName);
	public List<Staff> findByLastName(String lastName);
	public List<Staff> findByPhone(String phone);
	public List<Staff> findByEmail(String email);
	public List<Staff> findByCreateDate(Date createDate);
}
