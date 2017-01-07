package com.excilys.formation.computerdatabase.service.company;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.service.Service;

/**
 * @author GUIDS
 *
 * @param <T>
 *            : Company
 */
public interface CompanyService extends Service<Company> {

    /**
     * @return the number of companies
     */
    public int count();

    /**
     * @param id
     *            : the id of the company
     * @return the company specified
     */
    public Company getCompany(Long id);
}
