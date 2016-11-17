package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.LocalDateMapper;
import com.excilys.formation.computerdatabase.mapper.ResultToObjectMapper;
import com.excilys.formation.computerdatabase.model.Computer;

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
public class ComputerDao implements ComputerDaoInterface {

  private static ComputerDao computerDao = new ComputerDao(); //singleton of this class
  private static ResultToObjectMapper resultToObjectMapper = ResultToObjectMapper.getInstance(); //utility to manage resultSets
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
  private ComputerDao() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static ComputerDao getInstance() {
    return computerDao;
  }

  @Override
  public Computer insert(Computer computer) throws ConnectionException {
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_REQUEST)) {
      preparedStatement.setInt(1,computer.getId());
      preparedStatement.setString(2,computer.getName());
      preparedStatement.setObject(3,localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(4,localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(5,computer.getCompany().getId());
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be inserted");
    }
    return computer;
  }

  @Override
  public Computer update(Computer computer) throws ConnectionException {
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_REQUEST)) {
      preparedStatement.setString(1, computer.getName());
      preparedStatement.setObject(2, localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(3, localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(4, computer.getCompany().getId());
      preparedStatement.setInt(5, computer.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be updated");
    }
    return computer;
  }

  @Override
  public void delete(int computerId) throws ConnectionException {
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_REQUEST)) {
      preparedStatement.setInt(1, computerId);
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be deleted");
    }
  }

  @Override
  public List<Computer> list(int nbComputers, int offset) throws ConnectionException {
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(LIST_REQUEST)) {
      preparedStatement.setInt(1, nbComputers);
      preparedStatement.setInt(2, offset);
      return resultToObjectMapper.convertToComputers(preparedStatement.executeQuery());
    } catch (SQLException exception) {
      throw new ConnectionException("computers failed to be listed");
    }
  }

  @Override
  public Computer showComputerDetails(int computerId) throws ConnectionException {
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(DETAILS_REQUEST)) {
      preparedStatement.setInt(1, computerId);
      Computer computer = resultToObjectMapper.convertToComputer(preparedStatement.executeQuery());
      System.out.println(computer);
      return computer;
    } catch (SQLException exception) {
      throw new ConnectionException("computer failed to be detailed");
    }
  }

  @Override
  public int getNumber() throws ConnectionException {
    int numberComputers = -1; 
    try { 
      Statement statement = jdbcConnection.openConnection().createStatement();
      results = statement.executeQuery(NUMBER_REQUEST); 
      results.next();
      numberComputers = results.getInt("number");
    } catch (SQLException exception) { 
      throw new ConnectionException("computers failed to be counted");
    } 
    return numberComputers; 
  } 

}
