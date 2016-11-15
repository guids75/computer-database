package com.excilys.formation.computerdatabase.persistence;

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

public class ComputerDao implements ComputerDaoInterface<Computer> {

  private static ComputerDao computerDao = new ComputerDao();  
  private static final ResultToObjectMapper resultToObjectMapper =
      ResultToObjectMapper.getInstance();
  private static final JdbcConnection jdbcConnection = JdbcConnection.getInstance();
  private static final LocalDateMapper localDateMapper = LocalDateMapper.getInstance();

  private static ResultSet results;
  private static String request;

  private ComputerDao() {
  }

  public static ComputerDao getInstance() {
    return computerDao;
  }

  /** 
   * 
   */
  public Computer insert(Computer computer) {
    request = "INSERT INTO computer VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      preparedStatement.setInt(1,computer.getId());
      preparedStatement.setString(2,computer.getName());
      preparedStatement.setObject(3,localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(4,localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(5,computer.getCompany().getId());
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return computer;
  }

  /**
   * 
   */
  public Computer update(Computer computer) {
    request = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE id=?";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      preparedStatement.setString(1, computer.getName());
      preparedStatement.setObject(2, localDateMapper.convertToTimeStamp(computer.getIntroduced()));
      preparedStatement.setObject(3, localDateMapper.convertToTimeStamp(computer.getDiscontinued()));
      preparedStatement.setInt(4, computer.getCompany().getId());
      preparedStatement.setInt(5, computer.getId());
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return computer;
  }

  /**
   * 
   * @param computerId
   */
  public void delete(int computerId) {
    request = "DELETE FROM computer WHERE id=?";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      preparedStatement.setInt(1, computerId);
      preparedStatement.executeUpdate();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * 
   */
  public List<Computer> list(int nbComputers, int offset) {
    request = "SELECT * FROM computer as comput, company as compan WHERE comput.company_id=compan.id LIMIT ? OFFSET ?";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
        preparedStatement.setInt(1, nbComputers);
        preparedStatement.setInt(2, offset);
      return resultToObjectMapper.convertToComputers(preparedStatement.executeQuery());
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * 
   */
  public Computer showComputerDetails(int computerId) {
    request = "SELECT * FROM computer as comput, company as compan WHERE comput.id=? and comput.id=compan.id";
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      preparedStatement.setInt(1, computerId);
      Computer computer = resultToObjectMapper.convertToComputer(preparedStatement.executeQuery());
      System.out.println(computer);
      return computer;
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }
  
  public int getNumber(){
	  int numberComputers = -1; 
	  request = "SELECT COUNT(*) as number FROM computer";
	  try { 
	    Statement statement = jdbcConnection.openConnection().createStatement();
	    results = statement.executeQuery(request); 
	    results.next();
	    numberComputers = results.getInt("number");
	  } catch (SQLException e) { 
	    e.printStackTrace(); 
	  } 
	  return numberComputers; 
  } 

}
