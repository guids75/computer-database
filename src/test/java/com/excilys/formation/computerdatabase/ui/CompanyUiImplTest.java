/**
 * 
 */
package com.excilys.formation.computerdatabase.ui;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.excilys.formation.computerdatabase.ui.company.CompanyUiImpl;
import com.excilys.formation.computerdatabase.dto.CompanyDto;

/**
 * @author GUIDS
 *
 */
public class CompanyUiImplTest {

  CompanyUiImpl companyUi;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    companyUi = new CompanyUiImpl();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testList() {
    fail("Not yet implemented");
  }
  
  @Test
  public void testDelete() {
    fail("Not yet implemented");
  }
  
  @Test
  public void testPrint() {
    List<CompanyDto> companies = new ArrayList<>();
    companyUi.print(companies);
    companies.add(new CompanyDto());
    companyUi.print(companies);
  }
  
  
  
  

}
