package com.excilys.formation.computerdatabase.persistence;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

public class ComputerDaoImplTest {

    @Autowired
    private ComputerDao computerDao;

    @Test
    public void testInsert() {
        int count = computerDao.count(new Constraints.ConstraintsBuilder().build());
        Computer computer = new Computer.Builder("MyComputer").introduced(LocalDate.parse("1990-02-02"))
                .discontinued(LocalDate.parse("1991-02-02"))
                .company(new Company.Builder("Company").id(5L).build()).build();
        Long computer2 = computerDao.insert(computer);
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
