package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by excilys on 06/01/17.
 */
public class CompanyDtoMapperTest {

    private Company company;
    private Company company2;
    private CompanyDto companyDto;
    private CompanyDto companyDto2;
    private List<Company> listCompany;
    private List<CompanyDto> listCompanyDto;


    @Before
    public void setUp() throws Exception {
        company = new Company.Builder("comp1").id(1L).build();
        company2 = new Company.Builder("comp2").id(2L).build();
        companyDto = new CompanyDto.CompanyDtoBuilder("compdto1").id(3L).build();
        companyDto2 = new CompanyDto.CompanyDtoBuilder("compdto2").id(4L).build();
        listCompany = new ArrayList<>();
        listCompany.add(company);
        listCompany.add(company2);
        listCompanyDto = new ArrayList<>();
        listCompanyDto.add(companyDto);
        listCompanyDto.add(companyDto2);
    }


    @Test
    public void companyDtoListToCompanyList() throws Exception {
        List<Company> listTest = CompanyDtoMapper.companyDtoListToCompanyList(listCompanyDto);
        assertNotNull("companyDtoList to companyList : not null", listTest);
        assertTrue("companyDtoList to companyList : companies in", listTest.size() == 2);
    }

    @Test
    public void companyDtoToCompany() throws Exception {
        Company companyTest = CompanyDtoMapper.companyDtoToCompany(companyDto);
        assertNotNull("companyDto to company : not null", companyTest);
        assertTrue("companyDto to company : company in", companyTest.getId() == companyDto.getId());
    }

    @Test
    public void companyListToCompanyDtoList() throws Exception {
        List<CompanyDto> listTest = CompanyDtoMapper.companyListToCompanyDtoList(listCompany);
        assertNotNull("companyList to companyDtoList : not null", listTest);
        assertTrue("companyList to companyDtoList : companies in", listTest.size() == 2);
    }

    @Test
    public void companyToCompanyDto() throws Exception {
        CompanyDto companyDtoTest = CompanyDtoMapper.companyToCompanyDto(company);
        assertNotNull("company to companyDto : not null", companyDtoTest);
        assertTrue("company to companyDto : company in", companyDtoTest.getId() == company.getId());
    }

}