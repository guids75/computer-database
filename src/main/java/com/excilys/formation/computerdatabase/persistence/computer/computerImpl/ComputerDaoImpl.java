package com.excilys.formation.computerdatabase.persistence.computer.computerImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import javax.ejb.EJBException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.computer.ComputerDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

/**
 * @author GUIDS
 *
 */
public enum ComputerDaoImpl implements ComputerDao {

    INSTANCE;

    // requests
    private static final String INSERT_REQUEST = "INSERT INTO computer(name, introduced, discontinued, company_id) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_REQUEST = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    private static final String DELETE_REQUEST = "DELETE FROM computer WHERE id";
    private static final String LIST_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id LIMIT ? OFFSET ?";
    private static final String DETAILS_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer WHERE computer.id=? LEFT JOIN company ON computer.company_id=company.id";
    private static final String NUMBER_REQUEST = "SELECT COUNT(computer.id) as number FROM computer";
    private static final String SEARCH_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
    private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
    private static final String LISTBYCOMPANY_REQUEST = "SELECT computer.id as computerId, computer.name as computerName, computer.introduced, computer.discontinued, company.id as companyId, company.name as companyName"
            + " FROM computer LEFT JOIN company ON computer.company_id=company.id WHERE company.id=?";

    private static HikariConnectionPool hikariConnectionPool = 
            HikariConnectionPool.INSTANCE; // get the connection
    private ResultSet results;

    @Override
    public Computer insert(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to insert");
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST,
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setObject(2, computer.getIntroduced());
            preparedStatement.setObject(3, computer.getDiscontinued());
            preparedStatement.setLong(4, computer.getCompany().getId());
            preparedStatement.executeUpdate();
            results = preparedStatement.getGeneratedKeys();
            if (results != null && results.next()) {
                computer.setId(results.getLong(1));
            }
            results.close();
        } catch (SQLException exception) {
            throw new ConnectionException("computer failed to be inserted", exception);
        }
        return computer;
    }

    @Override
    public Computer update(Computer computer) throws ConnectionException {
        if (computer == null) {
            throw new IllegalArgumentException("A computer is missing to update");
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
            preparedStatement.setString(1, computer.getName());
            preparedStatement.setObject(2, computer.getIntroduced());
            preparedStatement.setObject(3, computer.getDiscontinued());
            preparedStatement.setLong(4, computer.getCompany().getId());
            preparedStatement.setLong(5, computer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ConnectionException("computer failed to be updated", exception);
        }
        return computer;
    }

    @Override
    public void delete(Constraints constraints, Connection connection) throws ConnectionException {
        if (constraints == null | (constraints.getIdList() == null | connection == null)) {
            throw new IllegalArgumentException("A connection is missing to delete or the constraints are null");
        }
        String request;
        if (constraints.getIdList().size() == 1) {
            request = DELETE_REQUEST + "=?";
        } else {
            request = DELETE_REQUEST + " IN " + constraints.getIdList().toString().replace('[', '(').replace(']', ')');
        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            if (constraints.getIdList().size() == 1) {
                preparedStatement.setLong(1, constraints.getIdList().get(0));
            }
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new ConnectionException("computer failed to be deleted", exception);
        }
    }

    @Override
    public List<Computer> list(Constraints constraints) throws ConnectionException {
        if (constraints == null | (constraints.getLimit() == -1 || constraints.getOffset() == -1)) {
            throw new IllegalArgumentException("Constraints are missing to list");
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
            preparedStatement.setInt(1, constraints.getLimit());
            preparedStatement.setInt(2, constraints.getOffset());
            List<Computer> list = ResultMapper.convertToComputers(preparedStatement.executeQuery());
            return list;
        } catch (SQLException exception) {
            throw new ConnectionException("computers failed to be listed", exception);
        }
    }

    @Override
    public List<Long> listByCompany(Constraints constraints, Connection connection) throws ConnectionException {
        if (constraints == null | (constraints.getIdCompany() == -1 || connection == null)) {
            throw new IllegalArgumentException("Constraints are missing to listByCompany");
        } 
        try (PreparedStatement preparedStatement = connection.prepareStatement(LISTBYCOMPANY_REQUEST)) {
            preparedStatement.setLong(1, constraints.getIdCompany());
            List<Long> list = ResultMapper.convertToComputersId(preparedStatement.executeQuery());
            return list;
        } catch (SQLException exception) {
            throw new ConnectionException("computers failed to be listed", exception);
        }
    }

    @Override
    public Computer showComputerDetails(Long computerId) throws ConnectionException {
        if (computerId < 1) {
            throw new IllegalArgumentException("The computerId is wrong to showComputerDetails : must be more than 0");
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DETAILS_REQUEST)) {
            preparedStatement.setLong(1, computerId);
            Computer computer = ResultMapper.convertToComputer(preparedStatement.executeQuery());
            return computer;
        } catch (SQLException exception) {
            throw new ConnectionException("computer failed to be detailed", exception);
        }
    }

    @Override
    public int count(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to count");
        }
        int numberComputers = -1;
        String request;
        if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
            request = "SELECT COUNT(*) As number FROM (" + SEARCH_REQUEST + ") as derivedTable";
        } else {
            request = NUMBER_REQUEST;
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(request)) {
            if (constraints.getSearch() != null && !constraints.getSearch().equals("")) {
                preparedStatement.setString(1, "%" + constraints.getSearch() + "%");
                preparedStatement.setString(2, "%" + constraints.getSearch() + "%");
            }
            results = preparedStatement.executeQuery();
            results.next();
            numberComputers = results.getInt("number");
        } catch (SQLException exception) {
            throw new ConnectionException("computers failed to be counted", exception);
        }
        return numberComputers;
    }

    @Override
    public List<Computer> search(Constraints constraints) throws ConnectionException {
        if (constraints == null) {
            throw new IllegalArgumentException("Constraints are missing to search");
        }
        try (Connection connection = hikariConnectionPool.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_REQUEST + LIMIT_OFFSET)) {
            preparedStatement.setString(1, "%" + constraints.getSearch() + "%");
            preparedStatement.setString(2, "%" + constraints.getSearch() + "%");
            preparedStatement.setInt(3, constraints.getLimit());
            preparedStatement.setInt(4, constraints.getOffset());
            List<Computer> list = ResultMapper.convertToComputers(preparedStatement.executeQuery());
            return list;
        } catch (SQLException exception) {
            throw new ConnectionException("computers failed to be searched", exception);
        }
    }

}
