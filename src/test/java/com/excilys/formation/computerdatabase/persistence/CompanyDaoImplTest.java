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
  private HikariConnectionPool hikariConnectionPool = HikariConnectionPool.INSTANCE;

  @Before
  public void setUp() throws Exception {
    CompanyDaoImpl companyDao = CompanyDaoImpl.INSTANCE;
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testList() {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection()) {
      List<Company> companies = companyDao.list(new Constraints.ConstraintsBuilder().limit(10).offset(0).build(), connection);
      assertNotNull(companies);
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}
