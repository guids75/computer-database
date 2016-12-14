package com.excilys.formation.computerdatabase.persistence;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDaoImpl;

public class ComputerDaoImplTest {

    private ComputerDaoImpl computerDao;
    private Connection connection = HikariConnectionPool.INSTANCE.getConnection();

    @Before
    public void setUp() throws Exception {
        computerDao = ComputerDaoImpl.INSTANCE;
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsert() {
        int count = computerDao.count(new Constraints.ConstraintsBuilder().build());
        Computer computer = new Computer.ComputerBuilder("MyComputer").introduced(LocalDate.parse("1990-02-02"))
                .discontinued(LocalDate.parse("1991-02-02"))
                .company(new Company.CompanyBuilder("Company").id(5L).build()).build();
        Computer computer2 = computerDao.insert(computer);
        assertNotNull("insert", computer2);
        assertTrue("insert", count + 1 == computerDao.count(new Constraints.ConstraintsBuilder().build()));
    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    @Test
    public void testList() {
        fail("Not yet implemented");
    }

    @Test
    public void testShowComputerDetails() {
        fail("Not yet implemented");
    }

}
