package com.excilys.formation.computerdatabase.service;

import com.excilys.formation.computerdatabase.model.Company;

public interface CompanyServiceInterface<T> extends ServiceInterface<T> {

	public int getNumber();
	
	public Company getCompany(int id);
}
