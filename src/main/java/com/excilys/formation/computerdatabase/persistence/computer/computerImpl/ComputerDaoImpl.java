package com.excilys.formation.computerdatabase.persistence.computer.computerImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import javax.ejb.EJBException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.Constraints;
import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;
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
  private static HikariConnectionPool hikariConnectionPool = 
      HikariConnectionPool.INSTANCE; // get the connection

  // requests
  private static final String INSERT_REQUEST = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
  private static final String UPDATE_REQUEST = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
  private static final String DELETE_REQUEST = "DELETE FROM computer WHERE id";
  private static final String LIST_REQUEST = "SELECT * FROM computer as comput LEFT JOIN company as compan ON comput.company_id=compan.id LIMIT ? OFFSET ?";
  private static final String DETAILS_REQUEST = "SELECT * FROM computer as comput WHERE comput.id=? LEFT JOIN company as compan ON comput.id=compan.id";
  private static final String NUMBER_REQUEST = "SELECT COUNT(*) as number FROM computer";
  private static final String SEARCH_REQUEST = "SELECT * FROM computer as comput LEFT JOIN company as compan ON comput.company_id=compan.id "
      + "WHERE comput.name LIKE ? OR compan.name LIKE ?";
  private static final String LIMIT_OFFSET = " LIMIT ? OFFSET ?";
  private static final String LISTBYCOMPANY_REQUEST = "SELECT comput.id FROM computer as comput LEFT JOIN company as compan ON comput.company_id=compan.id WHERE compan.id=?";

  private ResultSet results;
  private Connection connection = null;

  @Override
  public Computer insert(Computer computer) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
        preparedStatement.setLong(1, computer.getId());
        preparedStatement.setString(2, computer.getName());
        preparedStatement.setObject(3, computer.getIntroduced());
        preparedStatement.setObject(4, computer.getDiscontinued());
        preparedStatement.setLong(5, computer.getCompany().getId());
        preparedStatement.executeUpdate();
      } catch (SQLException exception) {
        exception.printStackTrace();
        throw new ConnectionException("computer failed to be inserted", exception);
      }
      return computer;
  }

  @Override
  public Computer update(Computer computer) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
      preparedStatement.setString(1, computer.getName());
      preparedStatement.setObject(2, computer.getIntroduced());
      preparedStatement.setObject(3, computer.getDiscontinued());
      preparedStatement.setLong(4, computer.getCompany().getId());
      preparedStatement.setLong(5, computer.getId());
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      exception.printStackTrace();
      throw new ConnectionException("computer failed to be updated", exception);
    }
    return computer;
  }

  @Override
  public void delete(Constraints constraints, Connection connection) throws ConnectionException {
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
        exception.printStackTrace();
        throw new ConnectionException("computer failed to be deleted", exception);
      }
    }

    @Override
    public List<Computer> list(Constraints constraints, Connection connection) throws ConnectionException {
      try (PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
        preparedStatement.setInt(1, constraints.getLimit());
        preparedStatement.setInt(2, constraints.getOffset());
        List<Computer> list = ResultMapper.convertToComputers(preparedStatement.executeQuery());
        return list;
      } catch (SQLException exception) {
        exception.printStackTrace();
        throw new ConnectionException("computers failed to be listed", exception);
      }
    }
    
    @Override
    public List<Long> listByCompany(Constraints constraints, Connection connection) throws ConnectionException {
      try (PreparedStatement preparedStatement = connection.prepareStatement(LISTBYCOMPANY_REQUEST)) {
        preparedStatement.setLong(1, constraints.getIdCompany());
        List<Long> list = ResultMapper.convertToComputersId(preparedStatement.executeQuery());
        return list;
      } catch (SQLException exception) {
        exception.printStackTrace();
        throw new ConnectionException("computers failed to be listed", exception);
      }
    }

    @Override
    public Computer showComputerDetails(long computerId) throws ConnectionException {
      try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(DETAILS_REQUEST)) {
        preparedStatement.setLong(1, computerId);
        Computer computer = ResultMapper.convertToComputer(preparedStatement.executeQuery());
        return computer;
      } catch (SQLException exception) {
        exception.printStackTrace();
        throw new ConnectionException("computer failed to be detailed", exception);
      }
    }

    @Override
    public int count(Constraints constraints) throws ConnectionException {
      int numberComputers = -1;
      String request;
      /*if (constraints.getSearch() != null && !constraints.getSearch().equals("")){
      request = "SELECT COUNT (*) FROM (" + SEARCH_REQUEST + ") as table";
    } else {*/
      request = NUMBER_REQUEST;
      //}
      try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(request)) {
        /*if (constraints.getSearch() != null && !constraints.getSearch().equals("")){
        preparedStatement.setString(1, "%" + search + "%");
        preparedStatement.setString(2, "%" + search + "%");
      }*/
        results = preparedStatement.executeQuery();
        results.next();
        numberComputers = results.getInt("number");
      } catch (SQLException exception) {
        exception.printStackTrace();
        throw new ConnectionException("computers failed to be counted", exception);
      }
      return numberComputers;
    }

    @Override
    public List<Computer> search(Constraints constraints) throws ConnectionException {
      try (Connection connection = hikariConnectionPool.getDataSource().getConnection();
          PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_REQUEST + LIMIT_OFFSET)) {
        preparedStatement.setString(1, "%" + constraints.getSearch() + "%");
        preparedStatement.setString(2, "%" + constraints.getSearch() + "%");
        preparedStatement.setInt(3, constraints.getLimit());
        preparedStatement.setInt(4, constraints.getOffset());
        List<Computer> list = ResultMapper.convertToComputers(preparedStatement.executeQuery());
        return list;
      } catch (SQLException exception) {
          exception.printStackTrace();
          throw new ConnectionException("computers failed to be searched", exception);
      }
    }

  }
