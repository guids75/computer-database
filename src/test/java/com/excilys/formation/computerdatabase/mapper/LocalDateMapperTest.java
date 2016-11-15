package com.excilys.formation.computerdatabase.mapper;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.MockitoAnnotations;

public class LocalDateMapperTest {
  
  private LocalDateMapper localDateMapper = LocalDateMapper.getInstance();

  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
  }

  @AfterClass
  public static void tearDownAfterClass() throws Exception {
  }

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @After
  public void tearDown() throws Exception {
  }

  @Test
  public void testConvertToLocalDate() {
    Date date = new Date();
    assertNotNull(localDateMapper.convertToLocalDate(date));
    assertNull(localDateMapper.convertToLocalDate(null));
    //fail("Not yet implemented");
  }

  @Test
  public void testConvertToTimeStamp() {
    //fail("Not yet implemented");
  }
  
  private class MyDateMatcher extends ArgumentMatcher<Date> {
    
    @Override
    public boolean matches(Object argument) {
        Date d = (Date) argument;
        return d != null && d.after(new Date());
    }
}

}
