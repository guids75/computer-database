package com.excilys.formation.computerdatabase.persistence.computer.computerImpl;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import javax.ejb.EJBException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Computer;
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
public class ComputerDaoImpl implements ComputerDao {

  private static ComputerDaoImpl computerDao = new ComputerDaoImpl(); //singleton of this class
  private static HikariConnectionPool hikariConnectionPool = HikariConnectionPool.getInstance(); //get the connection

  //requests
  private static final String INSERT_REQUEST = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
  private static final String UPDATE_REQUEST = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
  private static final String DELETE_REQUEST = "DELETE FROM computer WHERE id=?";
  private static final String LIST_REQUEST= "SELECT * FROM computer as comput LEFT JOIN company as compan ON comput.company_id=compan.id LIMIT ? OFFSET ?";
  private static final String DETAILS_REQUEST = "SELECT * FROM computer as comput, company as compan WHERE comput.id=? and comput.id=compan.id";
  private static final String NUMBER_REQUEST = "SELECT COUNT(*) as number FROM computer";

  private ResultSet results;
  private Connection connection = null;

  /**
   * Private constructor for singleton.
   */
  private ComputerDaoImpl() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static ComputerDaoImpl getInstance() {
    return computerDao;
  }

  @Override
  public Computer insert(Computer computer) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setInt(1,computer.getId());
      preparedStatement.setString(2,computer.getName());
      preparedStatement.setObject(3,computer.getIntroduced());
      preparedStatement.setObject(4,computer.getDiscontinued());
      preparedStatement.setInt(5,computer.getCompany().getId());
      preparedStatement.executeUpdate();
      connection.commit();
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      exception.printStackTrace();
      throw new ConnectionException("computer failed to be inserted", exception);
    }
    return computer;
  }

  @Override
  public Computer update(Computer computer) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setString(1, computer.getName());
      preparedStatement.setObject(2, computer.getIntroduced());
      preparedStatement.setObject(3, computer.getDiscontinued());
      preparedStatement.setInt(4, computer.getCompany().getId());
      preparedStatement.setInt(5, computer.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();
      connection.commit();
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      throw new ConnectionException("computer failed to be updated", exception);
    }
    return computer;
  }

  @Override
  public void delete(int computerId) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setInt(1, computerId);
      preparedStatement.executeUpdate();
      connection.commit();
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      throw new ConnectionException("computer failed to be deleted", exception);
    }
  }

  @Override
  public List<Computer> list(int nbComputers, int offset) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setInt(1, nbComputers);
      preparedStatement.setInt(2, offset);
      List<Computer> list = ResultMapper.convertToComputers(preparedStatement.executeQuery());
        connection.commit();
        return list;
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      throw new ConnectionException("computers failed to be listed", exception);
    }
  }

  @Override
  public Computer showComputerDetails(int computerId) throws ConnectionException {
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DETAILS_REQUEST)) {
      connection.setAutoCommit(false);
      preparedStatement.setInt(1, computerId);
      Computer computer = ResultMapper.convertToComputer(preparedStatement.executeQuery());
      connection.commit();
      return computer;
    } catch (SQLException exception) {
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      throw new ConnectionException("computer failed to be detailed", exception);
    }
  }

  @Override
  public int count() throws ConnectionException {
    int numberComputers = -1; 
    try (Connection connection = hikariConnectionPool.getDataSource().getConnection(); 
        Statement statement = connection.createStatement()) {
      connection.setAutoCommit(false);
      results = statement.executeQuery(NUMBER_REQUEST); 
      results.next();
      numberComputers = results.getInt("number");
      connection.commit();
    } catch (SQLException exception) { 
      try {
        connection.rollback();
      } catch (SQLException sqx) {
        throw new EJBException("Rollback failed: " +
            sqx.getMessage());
      }
      throw new ConnectionException("computers failed to be counted", exception);
    } 
    return numberComputers; 
  } 

}
