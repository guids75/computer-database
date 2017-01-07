package com.excilys.formation.computerdatabase.ui.company;

import java.util.List;

import com.excilys.formation.computerdatabase.dto.CompanyDto;
import com.excilys.formation.computerdatabase.ui.Ui;

public interface CompanyUi extends Ui {

    public void delete();

    /**
     * Print the list of companies.
     * 
     * @param companies
     *          : list of companies to print
     */
    public void print(List<CompanyDto> companies);
}
