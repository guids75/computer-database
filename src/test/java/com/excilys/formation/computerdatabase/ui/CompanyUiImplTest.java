/**
 * 
 */
package com.excilys.formation.computerdatabase.ui;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.formation.computerdatabase.ui.company.CompanyUi;
import com.excilys.formation.computerdatabase.dto.CompanyDto;

/**
 * @author GUIDS
 *
 */
public class CompanyUiImplTest {

    @Autowired
    CompanyUi companyUi;

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
