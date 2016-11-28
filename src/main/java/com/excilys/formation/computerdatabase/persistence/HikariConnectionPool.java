package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.util.PropertiesReader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum HikariConnectionPool {

  INSTANCE;
  private static DataSource datasource;
  private static final String PROP_FILE_NAME = 
      "hikariConnection.properties"; // file to manage connection properties
  private static final Logger slf4jLogger = LoggerFactory.getLogger(HikariConnectionPool.class);
  public static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();
  private Connection connection = null;


  public DataSource getDataSource() {
    if (datasource == null) {
      try {
        Properties properties = PropertiesReader.getInstance().getPropValues(PROP_FILE_NAME);
        Class.forName(properties.getProperty("className"));
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("login"));
        config.setPassword(properties.getProperty("password"));

        datasource = new HikariDataSource(config);
        return datasource;
      } catch (IOException | ClassNotFoundException exception) {
        slf4jLogger.error("Problem with HikariConnection", exception);
      }
    }
    return datasource;
  }

  public Connection getConnection(){

    if(threadConnection.get() == null) {
      try {
        connection = getDataSource().getConnection();
      } catch (SQLException e) {
      }
      threadConnection.set(connection);
      return threadConnection.get();
    } else
      return threadConnection.get();
  } 

  private void closeConnection(Connection connection) throws SQLException {
    connection.close();
    threadConnection.remove();
  }

}