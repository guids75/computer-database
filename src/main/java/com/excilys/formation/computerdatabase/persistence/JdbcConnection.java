package com.excilys.formation.computerdatabase.persistence;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.util.PropertiesReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum JdbcConnection {

  INSTANCE;
  private static Connection connection = null; // connection
  private static final String PROP_FILE_NAME =
      "jdbcConnection.properties"; // file to manage connection properties
  private static final Logger slf4jLogger = LoggerFactory.getLogger(JdbcConnection.class);

  /**
   * Open a jdbc connection.
   * 
   * @return the connection opened
   */
  public void openConnection() {
    try {
      Properties properties = PropertiesReader.getInstance().getPropValues(PROP_FILE_NAME);
      Class.forName(properties.getProperty("className"));
      connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("login"),
          properties.getProperty("password"));
    } catch (SQLException | ClassNotFoundException | IOException exception) {
      slf4jLogger.error("Problem with JdbcConnection", exception);
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
        slf4jLogger.error("Problem while closing JdbcConnection", exception);
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
