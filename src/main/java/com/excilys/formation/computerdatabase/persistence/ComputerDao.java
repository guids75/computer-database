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
  public Computer create(Computer computer) {
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
  public List<Computer> list(int nbElements, int offset, boolean write) {
    request = "SELECT * FROM computer as comput, company as compan WHERE comput.company_id=compan.id"
        ;
    try (Connection connection = jdbcConnection.openConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(request)) {
      return resultToObjectMapper.convertToComputers(preparedStatement.executeQuery(), write);
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
      Computer computer = resultToObjectMapper.convertToComputer(preparedStatement.executeQuery(), true);
      return computer;
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
    return null;
  }

}
