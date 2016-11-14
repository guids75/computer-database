package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.util.PropertiesReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnection {  
  
  private static Connection connection = null;
  private static JdbcConnection jdbcConnection = new JdbcConnection();
  private static final String propFileName = "connection.properties";

  private JdbcConnection() {
  }


  public static JdbcConnection getInstance() {
    return jdbcConnection;
  }

  /**
   * 
   * @return
   */
  public Connection openConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties properties = PropertiesReader.getInstance().getPropValues(propFileName);
      connection = DriverManager.getConnection(properties.getProperty("url"),
          properties.getProperty("login"), properties.getProperty("password"));
      return connection;

    } catch (SQLException | ClassNotFoundException | IOException exception) {
      exception.printStackTrace();
    }
    return null;
  }

  /**
   * 
   */
  public void closeConnection() {
    try {
      connection.close();
    } catch (SQLException exception) {
      exception.printStackTrace();
    }
  }
  
  public Connection getConnection(){
	  return connection;
  }

}
