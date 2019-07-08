package com.erhan.springbootcrud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.erhan.springbootcrud.dao.StaffDAO;
import com.erhan.springbootcrud.model.Staff;

@RunWith(MockitoJUnitRunner.class)
public class StaffServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(StaffServiceTest.class);
	
	@Mock
	StaffDAO staffDAO;
	
	@InjectMocks
	StaffServiceImpl staffServiceImpl;
	
	@Test
	public void testCreate() {
		logger.info("testCreate is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date()); 
		when(staffDAO.save(staff)).thenReturn(staff);
		
		staffServiceImpl.create(staff);
		verify(staffDAO, times(1)).save(staff);
		
		logger.info("testCreate is successful.");
	}
	
	@Test
	public void testFindAll() throws ParseException {
		logger.info("testFindAll is started.");
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Staff staffMehmet = new Staff("Mehmet", "TEMBEL", "2783982736", "mehmet@abc.com", df.parse("2019-07-08 14:40:52"));
		staffMehmet.setId(Short.valueOf("1"));
		Staff staffAyse = new Staff("Ayşe", "HIZLI", "3892883737", "ayse@abc.com", df.parse("2019-07-08 14:38:38"));
		staffAyse.setId(Short.valueOf("2"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staffMehmet);
		staffList.add(staffAyse);
		when(staffDAO.findAll()).thenReturn(staffList);
		
		List<Staff> allStaff = staffServiceImpl.findAll();
		assertEquals(allStaff.size(), 2);
		verify(staffDAO, times(1)).findAll();
		logger.info("testFindAll is successful.");
	}
	
	@Test
	public void testFindById() {
		logger.info("testFindById is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staff.setId(Short.valueOf("1"));
		when(staffDAO.findById(Short.valueOf("1"))).thenReturn(Optional.of(staff));
		Staff staffFromDb = staffServiceImpl.findById(Short.valueOf("1"));
		assertNotNull(staffFromDb);
		assertEquals(staffFromDb.getId(), Short.valueOf("1"));
		assertEquals(staffFromDb.getFirstName(), "Ahmet");
		verify(staffDAO, times(1)).findById(Short.valueOf("1"));
		logger.info("testFindById is succesful.");
	}
	
	@Test
	public void testDelete() {
		logger.info("testDelete is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staff.setId(Short.valueOf("1"));
		
		staffServiceImpl.delete(staff);
		verify(staffDAO, times(1)).delete(staff);
		
		logger.info("testDelete is successful.");
	}
	
	@Test
	public void testFindByFirstName() {
		logger.info("testFindByFirstName is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staff.setId(Short.valueOf("1"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staff);
		when(staffDAO.findByFirstName("Ahmet")).thenReturn(staffList);
		
		List<Staff> staffListFromDb = staffServiceImpl.findByFirstName("Ahmet");
		assertEquals(staffListFromDb.size(), 1);
		assertEquals(staffList.get(0).getFirstName(), "Ahmet");
		verify(staffDAO, times(1)).findByFirstName("Ahmet");
		
		logger.info("testFindByFirstName is successful.");
	}
	
	@Test
	public void testFindByLastName() {
		logger.info("testFindByLastName is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staff.setId(Short.valueOf("1"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staff);
		when(staffDAO.findByLastName("ÇALIŞKAN")).thenReturn(staffList);
		
		List<Staff> staffListFromDb = staffServiceImpl.findByLastName("ÇALIŞKAN");
		assertEquals(staffListFromDb.size(), 1);
		assertEquals(staffListFromDb.get(0).getLastName(), "ÇALIŞKAN");
		verify(staffDAO, times(1)).findByLastName("ÇALIŞKAN");
		
		logger.info("testFindByLastName is successful.");
	}
	
	@Test
	public void testFindByPhone() {
		logger.info("testFindByPhone is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staff.setId(Short.valueOf("1"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staff);
		
		when(staffDAO.findByPhone("3842883726")).thenReturn(staffList);
		
		List<Staff> staffListFromDb = staffServiceImpl.findByPhone("3842883726");
		assertEquals(staffListFromDb.size(), 1);
		assertEquals(staffListFromDb.get(0).getPhone(), "3842883726");
		verify(staffDAO, times(1)).findByPhone("3842883726");
		logger.info("testFindByPhone is successful.");
	}
	
	@Test
	public void testFindByEmail() {
		logger.info("testFindByEmail is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "ahmet@abc.com", new Date());
		staff.setId(Short.valueOf("1"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staff);
		
		when(staffDAO.findByEmail("ahmet@abc.com")).thenReturn(staffList);
		
		List<Staff> staffListFromDb = staffServiceImpl.findByEmail("ahmet@abc.com");
		assertEquals(staffListFromDb.size(), 1);
		assertEquals(staffListFromDb.get(0).getEmail(), "ahmet@abc.com");
		verify(staffDAO, times(1)).findByEmail("ahmet@abc.com");
		logger.info("testFindByEmail is successful.");
	}
	
	@Test
	public void testFindByCreateDate() throws ParseException {
		logger.info("testFindByCreateDate is started.");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Staff staffAyse = new Staff("Ayşe", "HIZLI", "3892883737", "ayse@abc.com", df.parse("2019-07-08 14:38:38"));
		staffAyse.setId(Short.valueOf("2"));
		List<Staff> staffList = new ArrayList<Staff>();
		staffList.add(staffAyse);
		
		when(staffDAO.findByCreateDate(df.parse("2019-07-08 14:38:38"))).thenReturn(staffList);
		
		List<Staff> staffListFromDb = staffServiceImpl.findByCreateDate(df.parse("2019-07-08 14:38:38"));
		assertEquals(staffListFromDb.size(), 1);
		assertEquals(staffListFromDb.get(0).getCreateDate(), df.parse("2019-07-08 14:38:38"));
		verify(staffDAO, times(1)).findByCreateDate(df.parse("2019-07-08 14:38:38"));
		logger.info("testFindByCreateDate is successful.");
	}
}
