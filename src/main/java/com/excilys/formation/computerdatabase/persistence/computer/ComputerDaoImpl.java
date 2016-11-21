package com.excilys.formation.computerdatabase.persistence.computer;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.LocalDateMapper;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.persistence.JdbcConnection;

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
  private static ResultMapper resultToObjectMapper = ResultMapper.getInstance(); //utility to manage resultSets
  private static JdbcConnection jdbcConnection = JdbcConnection.getInstance(); //get the connection
  private static LocalDateMapper localDateMapper = LocalDateMapper.getInstance(); //utility to manage dates

  //requests
  private static final String INSERT_REQUEST = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
  private static final String UPDATE_REQUEST = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
  private static final String DELETE_REQUEST = "DELETE FROM computer WHERE id=?";
  private static final String LIST_REQUEST= "SELECT * FROM computer as comput LEFT JOIN company as compan ON comput.company_id=compan.id LIMIT ? OFFSET ?";
  private static final String DETAILS_REQUEST = "SELECT * FROM computer as comput, company as compan WHERE comput.id=? and comput.id=compan.id";
  private static final String NUMBER_REQUEST = "SELECT COUNT(*) as number FROM computer";

  private static ResultSet results;

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
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
      preparedStatement.setInt(1,computer.getId());
      preparedStatement.setString(2,computer.getName());
      preparedStatement.setObject(3,localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(4,localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(5,computer.getCompany().getId());
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
exception.printStackTrace();
throw new ConnectionException("computer failed to be inserted", exception);
    }
    return computer;
  }

  @Override
  public Computer update(Computer computer) throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
      preparedStatement.setString(1, computer.getName());
      preparedStatement.setObject(2, localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(3, localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(4, computer.getCompany().getId());
      preparedStatement.setInt(5, computer.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be updated", exception);
    }
    return computer;
  }

  @Override
  public void delete(int computerId) throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
      preparedStatement.setInt(1, computerId);
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be deleted", exception);
    }
  }

  @Override
  public List<Computer> list(int nbComputers, int offset) throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
      preparedStatement.setInt(1, nbComputers);
      preparedStatement.setInt(2, offset);
      return resultToObjectMapper.convertToComputers(preparedStatement.executeQuery());
    } catch (SQLException exception) {
      throw new ConnectionException("computers failed to be listed", exception);
    }
  }

  @Override
  public Computer showComputerDetails(int computerId) throws ConnectionException {
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DETAILS_REQUEST)) {
      preparedStatement.setInt(1, computerId);
      Computer computer = resultToObjectMapper.convertToComputer(preparedStatement.executeQuery());
      return computer;
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be detailed", exception);
    }
  }

  @Override
  public int count() throws ConnectionException {
    int numberComputers = -1; 
    jdbcConnection.openConnection();
    try (Connection connection = jdbcConnection.getConnection(); 
        Statement statement = connection.createStatement()) {
      results = statement.executeQuery(NUMBER_REQUEST); 
      results.next();
      numberComputers = results.getInt("number");
    } catch (SQLException exception) { 
      throw new ConnectionException("computers failed to be counted", exception);
    } 
    return numberComputers; 
  } 

}
