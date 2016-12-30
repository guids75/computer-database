package com.excilys.formation.computerdatabase.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.excilys.formation.computerdatabase.model.Company;

public final class CompanyJdbcMapper implements RowMapper<Company> {

    public Company mapRow(ResultSet results, int rowNum) throws SQLException {
        Company.Builder company = new Company.Builder(results.getString("companyName"));
        company.id(results.getLong("companyId"));
        return company.build();
    }
}