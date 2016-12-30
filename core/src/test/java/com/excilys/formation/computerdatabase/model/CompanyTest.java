package com.excilys.formation.computerdatabase.model;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CompanyTest {

    private Company company;

    @Before
    public void setUp() throws Exception {
        company = new Company.Builder("MyCompany").id(1L).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testHashCode() {
        Company otherCompany = new Company.Builder("MyCompany").id(1L).build();
        assertTrue("Same hashcode", company.hashCode() == otherCompany.hashCode());
        otherCompany.setName("MyNewCompany");
        assertTrue("Different hashcodes", company.hashCode() != otherCompany.hashCode());
        otherCompany.setName(null);
        assertTrue("Hashcode for null name", otherCompany.hashCode() + "MyCompany".hashCode() == company.hashCode());
    }

    @Test
    public void testGetId() {
        assertTrue("Get id", company.getId() == 1);
    }

    @Test
    public void testSetId() {
        company.setId(2L);
        assertTrue("Set id", company.getId() == 2);
    }

    @Test
    public void testGetName() {
        assertEquals("Get name", company.getName(), "MyCompany");
    }

    @Test
    public void testSetName() {
        company.setName("OtherName");
        assertEquals("Set name", company.getName(), "OtherName");
    }

    @Test
    public void testEqualsObject() {
        Company otherCompany = new Company.Builder("MyCompany").id(1L).build();
        assertTrue("Equals different", company.equals(otherCompany));
        assertTrue("Equals same", company.equals(company));
        assertFalse("NotEquals null", company.equals(null));
        otherCompany.setName(null);
        assertFalse("NotEquals name null", company.equals(otherCompany));
        otherCompany.setName("OtherName");
        assertFalse("NotEquals different name", company.equals(otherCompany));
        company.setName(null);
        assertFalse("NotEquals name null", company.equals(otherCompany));
        otherCompany.setId(2L);
        assertFalse("NotEquals different id", company.equals(otherCompany));
        assertFalse("NotEquals different type", company.equals("string"));
    }
}
