package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.Properties;

import javax.sql.DataSource;

import com.excilys.formation.computerdatabase.util.PropertiesReader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariConnectionPool {

  private static DataSource datasource;
  private static HikariConnectionPool hikariConnection = new HikariConnectionPool(); //singleton of this class
  private static final String PROP_FILE_NAME = "hikariConnection.properties"; //file to manage connection properties

  /**
   * Private constructor for singleton.
   */
  private HikariConnectionPool() {
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static HikariConnectionPool getInstance() {
    return hikariConnection;
  }


  public static DataSource getDataSource() {
    if(datasource == null) {
      try {
        Properties properties = PropertiesReader.getInstance().getPropValues(PROP_FILE_NAME);
        Class.forName(properties.getProperty("className"));
        HikariConfig config = new HikariConfig();
        System.out.println(properties.getProperty("url") + "\n" + properties.getProperty("login") + "\n" + properties.getProperty("password"));
        config.setJdbcUrl(properties.getProperty("url"));
        config.setUsername(properties.getProperty("login"));
        config.setPassword(properties.getProperty("password"));

        datasource = new HikariDataSource(config);
        return datasource;
      } catch (IOException | ClassNotFoundException exception) {
        exception.printStackTrace();
      }
    }
    return datasource;
  }

}