package com.excilys.formation.computerdatabase.mapper;

import java.util.ArrayList;
import java.util.List;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.model.Company;

public final class CompanyDtoMapper {

    private CompanyDtoMapper() {
    }

    /**
     * @param pListDto
     * @return
     */
    public static List<Company> companyDtoListToCompanyList(List<CompanyDto> listCompanyDto) {
        List<Company> companies = new ArrayList<>();
        if (listCompanyDto != null) {
            listCompanyDto.forEach(companyDto-> companies.add(companyDtoToCompany(companyDto)));
        }
        return companies;
    }

    /**
     * @param companyDto
     * @return
     */
    public static Company companyDtoToCompany(CompanyDto companyDto) {
        if (companyDto != null) {
            return new Company.Builder(companyDto.getName()).id(companyDto.getId()).build();
        }
        return null;
    }

    /**
     * @param pList
     * @return
     */
    public static List<CompanyDto> companyListToCompanyDtoList(List<Company> listCompany) {
        List<CompanyDto> companiesDto = new ArrayList<>();
        if (listCompany != null) {
            listCompany.forEach(company-> companiesDto.add(companyToCompanyDto(company)));
        }
        return companiesDto;
    }

    /**
     * @param company
     * @return
     */
    public static CompanyDto companyToCompanyDto(Company company) {
        if (company != null) {
            CompanyDto companyDto = new CompanyDto();
            companyDto.setName(company.getName());
            companyDto.setId(company.getId());
            return companyDto;
        }
        return null;
    }

}
