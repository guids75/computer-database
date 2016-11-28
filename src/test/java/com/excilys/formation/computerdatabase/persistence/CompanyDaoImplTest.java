package com.excilys.formation.computerdatabase.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.company.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.company.companyImpl.CompanyDaoImpl;

public class CompanyDaoImplTest {

    private CompanyDaoImpl companyDao;
    private Connection connection = HikariConnectionPool.INSTANCE.getConnection();

    @Before
    public void setUp() throws Exception {
        companyDao = CompanyDaoImpl.INSTANCE;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testList() {
        List<Company> companies = companyDao.list(new Constraints.ConstraintsBuilder().limit(10).offset(0).build());
        assertNotNull("list", companies);
    }

    @Test
    public void testCount() {
        int count = companyDao.count();
        assertNotNull("count not null", count);
        assertTrue("count bigger than -1", count > -1);
    }

    @Test
    public void testGetCompany() {
        Company company = companyDao.getCompany(1L);
        assertNotNull("get company", company);
    }

}
