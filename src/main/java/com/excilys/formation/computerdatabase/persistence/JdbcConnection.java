package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.util.PropertiesReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author GUIDS
 *
 */
public class JdbcConnection {  

  private static Connection connection = null; //connection
  private static JdbcConnection jdbcConnection = new JdbcConnection(); //singleton of this class
  private static final String PROP_FILE_NAME = "connection.properties"; //file to manage connection properties

  /**
   * Private constructor for singleton.
   */
  private JdbcConnection() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static JdbcConnection getInstance() {
    return jdbcConnection;
  }

  /** Open a jdbc connection.
   * 
   * @return the connection opened
   */
  public void openConnection() {
    try {
      Class.forName("com.mysql.jdbc.Driver");
      Properties properties = PropertiesReader.getInstance().getPropValues(PROP_FILE_NAME);
      connection = DriverManager.getConnection(properties.getProperty("url"),
          properties.getProperty("login"), properties.getProperty("password"));
    } catch (SQLException | ClassNotFoundException | IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * Close a jdbc connection.
   */
  public void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException exception) {
        exception.printStackTrace();
      }
    }
  }

  /**
   * @return the current connection
   */
  public Connection getConnection() {
    return connection;
  }

}
