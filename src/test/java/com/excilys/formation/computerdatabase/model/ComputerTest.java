/**
 * 
 */
package com.excilys.formation.computerdatabase.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author GUIDS
 *
 */
public class ComputerTest {

  private Computer computer;
  
  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    computer = new Computer.ComputerBuilder("MyComputer").id(1).build();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {
  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#hashCode()}.
   */
  @Test
  public void testHashCode() {
    Computer otherComputer = new Computer.ComputerBuilder("MyComputer").id(1).build(); 
    assertTrue("Same hashcode", computer.hashCode() == otherComputer.hashCode()); 
    otherComputer.setName("MyNewComputer"); 
    assertTrue("Different hashcodes", computer.hashCode() != otherComputer.hashCode()); 
    otherComputer.setName(null);
    assertTrue("Hashcode for null name", otherComputer.hashCode() + "MyComputer".hashCode() == computer.hashCode());

  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#getId()}.
   */
  @Test
  public void testGetId() {
    assertTrue("Get id", computer.getId() == 1); 
  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#setId(long)}.
   */
  @Test
  public void testSetId() {
    computer.setId(2); 
    assertTrue("Set id", computer.getId() == 2); 
  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#getName()}.
   */
  @Test
  public void testGetName() {
    assertEquals("Get name", computer.getName(), "MyComputer"); 
  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#setName(java.lang.String)}.
   */
  @Test
  public void testSetName() {
    computer.setName("OtherComputerName"); 
    assertEquals("Set name", computer.getName(), "OtherComputerName"); 
  }

  /**
   * Test method for {@link com.excilys.formation.computerdatabase.model.Computer#equals(java.lang.Object)}.
   */
  @Test
  public void testEqualsObject() {
    Computer otherComputer = new Computer.ComputerBuilder("MyComputer").id(1).build(); 
    assertTrue("Equals different", computer.equals(otherComputer)); 
    assertTrue("Equals same", computer.equals(computer));
    assertFalse("NotEquals null", computer.equals(null));
    otherComputer.setName(null);
    assertFalse("NotEquals name null", computer.equals(otherComputer));
    otherComputer.setName("OtherName");
    assertFalse("NotEquals different name", computer.equals(otherComputer));
    computer.setName(null);
    assertFalse("NotEquals name null", computer.equals(otherComputer));
    otherComputer.setId(3);
    assertFalse("NotEquals different id", computer.equals(otherComputer));
    assertFalse("NotEquals different type", computer.equals("string"));
  }

}
