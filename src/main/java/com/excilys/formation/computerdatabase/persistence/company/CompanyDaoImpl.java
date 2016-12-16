package com.excilys.formation.computerdatabase.persistence.company;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.CompanyJdbcMapper;
import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.persistence.Constraints;

import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author GUIDS
 *
 */
@Repository
public class CompanyDaoImpl implements CompanyDao {

    // requests
    private static final String LIST_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company LIMIT ? OFFSET ?";
    private static final String DELETE_REQUEST = "DELETE FROM company WHERE id=?";
    private static final String NUMBER_REQUEST = "SELECT COUNT(company.id) as number FROM company";
    private static final String COMPANY_REQUEST = "SELECT company.id as companyId, company.name as companyName FROM company where id=?";

    private JdbcTemplate jdbcTemplate;

    public CompanyDaoImpl() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<Company> list(Constraints constraints) throws ConnectionException {
        if (constraints == null || (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("A limit or an offset is missing in the constraints");
        }
        return jdbcTemplate.query(LIST_REQUEST, new Object[] {constraints.getLimit(), constraints.getOffset() }, new CompanyJdbcMapper());
    }

    @Override
    public void delete(Constraints constraints) throws ConnectionException {
        if (constraints == null || (constraints.getIdCompany() == -1L)) {
            throw new IllegalArgumentException("A company is missing in the constraints or the connection is closed");
        }
        System.out.println(DELETE_REQUEST + " " + constraints.getIdCompany());
        jdbcTemplate.update(DELETE_REQUEST, constraints.getIdCompany());
    }

    @Override
    public int count() throws ConnectionException {
        return jdbcTemplate.queryForObject(NUMBER_REQUEST, Integer.class);
    }

    @Override
    public Company getCompany(Long id) throws ConnectionException {
        if (id < 1) {
            throw new IllegalArgumentException("The id given for the company is wrong");
        }
        return jdbcTemplate.queryForObject(COMPANY_REQUEST, new Object[] {id}, new CompanyJdbcMapper());
    }

}
