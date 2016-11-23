package com.excilys.formation.computerdatabase.mapper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.dto.ComputerDto;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

public final class CompanyDtoMapper {

  
  /**
   * @param pListDto
   * @return
   */
  public static List<Company> companyDtoListToCompanyList(List<CompanyDto> listCompanyDto) { 
    List<Company> companies = new ArrayList<>(); 
    if (listCompanyDto != null) { 
      for (CompanyDto companyDto : listCompanyDto) { 
        companies.add(companyDtoToCompany(companyDto)); 
      } 
    } 
    return companies; 
  } 
  
  /**
   * @param companyDto
   * @return
   */
  public static Company companyDtoToCompany(CompanyDto companyDto) {
    return new Company.CompanyBuilder(companyDto.getName())
        .id(companyDto.getId()).build();
  }

  /**
   * @param pList
   * @return
   */
  public static List<CompanyDto> companyListToCompanyDtoList(List<Company> listCompany) { 
    List<CompanyDto> companiesDto = new ArrayList<>(); 
    if (listCompany != null) { 
      for (Company company : listCompany) {
        companiesDto.add(companyToCompanyDto(company)); 
      } 
    } 
    return companiesDto; 
  } 
  
  /**
   * @param company
   * @return
   */
  public static CompanyDto companyToCompanyDto(Company company) {
    CompanyDto companyDto = new CompanyDto();
    companyDto.setName(company.getName());
    companyDto.setId(company.getId());
    return companyDto;
  }

}
