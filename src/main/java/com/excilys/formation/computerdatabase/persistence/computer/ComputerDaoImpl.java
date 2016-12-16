package com.excilys.formation.computerdatabase.persistence.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.excilys.formation.computerdatabase.mapper.ComputerJdbcMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

/**
 * @author GUIDS
 *
 */
@Repository
public class ComputerDaoImpl implements ComputerDao {

    // requests
    private static final String INSERT_REQUEST = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private static final String DELETE_REQUEST = "DELETE FROM computer WHERE id";
    private static final String LIST_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id";
    private static final String DETAILS_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.id=?";
    private static final String NUMBER_REQUEST = "SELECT COUNT(computer.id) as number FROM computer";
    private static final String SEARCH_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
    private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
    private static final String LISTBYCOMPANY_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.id=?";
    private static final String ORDER_BY = " ORDER BY ";

    @Autowired
    private DataSource dataSource; // get the connection
    private JdbcTemplate jdbcTemplate;

    public ComputerDaoImpl() {
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Computer insert(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to insert");
        }

        KeyHolder holder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {     
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST, PreparedStatement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, computer.getName());
                preparedStatement.setObject(2, computer.getIntroduced());
                preparedStatement.setObject(3, computer.getDiscontinued());
                preparedStatement.setLong(4, computer.getCompany().getId());
                return preparedStatement;
            }
        }, holder);

        computer.setId(holder.getKey().longValue());
        return computer;
    }

    @Override
    public Computer update(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to update");
        }
        jdbcTemplate.update(UPDATE_REQUEST, new Object[] { computer.getName(), computer.getIntroduced(), computer.getDiscontinued(), computer.getCompany().getId(), computer.getId()});
        return computer;
    }

    @Override
    public void delete(Constraints constraints) throws ConnectionException {
        if (constraints == null | (constraints.getIdList() == null)) {
            throw new IllegalArgumentException("A connection is missing to delete or the constraints are null");
        }
        if (constraints.getIdList().isEmpty()) {
            return;
        }
        String request;
        if (constraints.getIdList().size() == 1) {
            request = DELETE_REQUEST + "=?";
            jdbcTemplate.update(request, constraints.getIdList().get(0));
        } else {
            request = DELETE_REQUEST + " IN " + constraints.getIdList().toString().replace('[', '(').replace(']', ')');
            jdbcTemplate.update(request);
        }
    }

    @Override
    public List<Computer> list(Constraints constraints) throws ConnectionException {
        if (constraints == null | (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("Constraints are missing to list");
        }
        String request = LIST_REQUEST;
        if (constraints.getOrderBy() != null) {
            request += ORDER_BY + "ISNULL(" + constraints.getOrderBy() + "), " + constraints.getOrderBy() + " ASC" + LIMIT_OFFSET;
        } else {
            request += LIMIT_OFFSET;
        }
        return jdbcTemplate.query(request, new Object[] {constraints.getLimit(), constraints.getOffset() }, new ComputerJdbcMapper());
    }

    @Override
    public List<Long> listByCompany(Constraints constraints) throws ConnectionException {
        if (constraints == null | (constraints.getIdCompany() == -1)) {
            throw new IllegalArgumentException("Constraints are missing to listByCompany");
        } 
        return (List<Long>) jdbcTemplate.queryForList(LISTBYCOMPANY_REQUEST, new Object[] {constraints.getIdCompany() }, Long.class);
    }

    @Override
    public Computer showComputerDetails(Long computerId) throws ConnectionException {
        if (computerId < 1) {
            throw new IllegalArgumentException("The computerId is wrong to showComputerDetails : must be more than 0");
        }
        try {
            return jdbcTemplate.queryForObject(DETAILS_REQUEST, new Object[] {computerId }, new ComputerJdbcMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public int count(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to count");
        }
        String request;
        if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
            request = "SELECT COUNT(*) As number FROM (" + SEARCH_REQUEST + ") as derivedTable";
        } else {
            request = NUMBER_REQUEST;
        }

        if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
            return jdbcTemplate.queryForObject(request,new Object[] { "%" + constraints.getSearch() + "%", "%" + constraints.getSearch() + "%" }, Integer.class);
        }
        return jdbcTemplate.queryForObject(NUMBER_REQUEST, Integer.class);
    }

    @Override
    public List<Computer> search(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to search");
        }
        return jdbcTemplate.query(SEARCH_REQUEST + LIMIT_OFFSET, new Object[] {"%" + constraints.getSearch() + "%", "%" + constraints.getSearch() + "%", constraints.getLimit(), constraints.getOffset() }, new ComputerJdbcMapper());
    }

}
