package com.excilys.formation.computerdatabase.persistence;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.List;

import com.excilys.formation.computerdatabase.constraints.Constraints;
import com.excilys.formation.computerdatabase.exception.ConnectionException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:application-context-persistence-test.xml"})
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class ComputerDaoImplTest {

    @Autowired
    private ComputerDao computerDao;
    private Computer computerTest;

    @Before
    public void setUp() throws Exception {
        computerTest = new Computer.Builder("MyComputer").introduced(LocalDate.parse("1990-02-02"))
                .discontinued(LocalDate.parse("1991-02-02"))
                .company(new Company.Builder("Company").id(5L).build()).build();
    }

    @Test
    @Transactional
    public void testInsert() {
        int count = computerDao.count(new Constraints.ConstraintsBuilder().build());
        Long id = computerDao.insert(computerTest);
        assertTrue("insert : positive id", id > 0);
        assertTrue("insert", count + 1 == computerDao.count(new Constraints.ConstraintsBuilder().build()));
    }

    @Test
    @Transactional
    public void testUpdate() {
        computerTest.setName("NameChanged");
        Computer computerResult = computerDao.update(computerTest);
        assertTrue("update : computer updated returned", computerResult.getName().equals("NameChanged"));
        computerResult = computerDao.getById(computerTest.getId());
        assertTrue("update : computer updated", computerResult.getName().equals("NameChanged"));
    }


}
