package com.erhan.springbootcrud.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.erhan.springbootcrud.model.Staff;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class StaffDAOTest {

	public static final Logger logger = LoggerFactory.getLogger(StaffDAOTest.class);
	
	@Autowired
	StaffDAO staffDAO;
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testSave() {
		logger.info("testSave is started.");
		Staff staff = new Staff("Ahmet", "ÇALIŞKAN", "3842883726", "", new Date());
		staffDAO.save(staff);
		staffDAO.flush();
		
		assertNotNull(staff.getId());
		assertEquals(staff.getId(), Short.valueOf("3"));
		
		logger.info("test save is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindAll() {
		logger.info("testFindAll is started.");
			
		List<Staff> allStaff = staffDAO.findAll();
		assertEquals(allStaff.size(), 2);
		logger.info("testFindAll is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindById() {
		logger.info("testFindById is started.");
		
		Staff staffFromDb = staffDAO.findById(Short.valueOf("2")).orElse(null);
		assertNotNull(staffFromDb);
		assertEquals(staffFromDb.getFirstName(), "Ayşe");
		logger.info("testFindById is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testDelete() {
		logger.info("testDelete is started.");
		Staff staffToDelete = staffDAO.findById(Short.valueOf("2")).orElse(null);
		assertNotNull(staffToDelete);
		staffDAO.delete(staffToDelete);
		staffDAO.flush();
		
		List<Staff> allStaff = staffDAO.findAll();
		assertEquals(allStaff.size(), 1);
		logger.info("testDelete is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindByFirstName() {
		logger.info("testFindByFirstName is started.");
		List<Staff> staffListBasedOnFirstName = staffDAO.findByFirstName("Ayşe");
		assertEquals(staffListBasedOnFirstName.size(), 1);
		assertEquals(staffListBasedOnFirstName.get(0).getFirstName(), "Ayşe");
		logger.info("testFindByFirstName is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindByLastName() {
		logger.info("testFindByLastName is started.");
		List<Staff> staffListBasedOnLastName = staffDAO.findByLastName("HIZLI");
		assertEquals(staffListBasedOnLastName.size(), 1);
		assertEquals(staffListBasedOnLastName.get(0).getLastName(), "HIZLI");
		logger.info("testFindByLastName is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindByPhone() {
		logger.info("testFindByPhone is started.");
		List<Staff> staffListBasedOnPhone = staffDAO.findByPhone("2783982736");
		assertEquals(staffListBasedOnPhone.size(), 1);
		assertEquals(staffListBasedOnPhone.get(0).getPhone(), "2783982736");
		logger.info("testFindByPhone is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindByEmail() {
		logger.info("testFindByEmail is started.");
		List<Staff> staffListBasedOnEmail = staffDAO.findByEmail("mehmet@abc.com");
		assertEquals(staffListBasedOnEmail.size(), 1);
		assertEquals(staffListBasedOnEmail.get(0).getEmail(), "mehmet@abc.com");
		logger.info("testFindByEmail is successful.");
	}
	
	@Test
	@Sql(scripts = {"classpath:testData.sql"})
	public void testFindByCreateDate() throws ParseException {
		logger.info("testFindByCreateDate is started.");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date createDate = df.parse("2019-07-08 14:40:52");
		logger.info("create date = " + df.format(createDate));
		List<Staff> staffListBasedOnCreateDate = staffDAO.findByCreateDate(createDate);
		assertEquals(staffListBasedOnCreateDate.size(), 1);
		assertEquals(df.format(staffListBasedOnCreateDate.get(0).getCreateDate()), df.format(createDate));
		logger.info("testFindByCreateDate is successful.");
	}
}
