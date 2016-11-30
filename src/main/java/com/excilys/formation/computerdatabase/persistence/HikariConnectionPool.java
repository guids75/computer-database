package com.excilys.formation.computerdatabase.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.ejb.EJBException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.formation.computerdatabase.exception.ConnectionException;
import com.excilys.formation.computerdatabase.mapper.ResultMapper;
import com.excilys.formation.computerdatabase.util.PropertiesReader;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public enum HikariConnectionPool {

    INSTANCE;
    private static DataSource datasource;
    private static final String PROP_FILE_NAME = 
            "hikariConnection.properties"; // file to manage connection properties
    private static final Logger logger = LoggerFactory.getLogger(HikariConnectionPool.class);
    private static final ThreadLocal<Connection> threadConnection = new ThreadLocal<Connection>();

    public DataSource getDataSource() {
        if (datasource == null) {
            try {
                Properties properties = PropertiesReader.INSTANCE.getPropValues(PROP_FILE_NAME);
                Class.forName(properties.getProperty("className"));
                HikariConfig config = new HikariConfig();
                config.setJdbcUrl(properties.getProperty("url"));
                config.setUsername(properties.getProperty("login"));
                config.setPassword(properties.getProperty("password"));

                datasource = new HikariDataSource(config);
                return datasource;
            } catch (IOException | ClassNotFoundException exception) {
                logger.error("Problem with HikariConnection", exception);
            }
        }
        return datasource;
    }

    public ThreadLocal<Connection> geThreadLocal() {
        return threadConnection;
    }

    public Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException exception) {
            logger.error("Error in getConnection", exception);
        }
        return null;
    } 

    public Connection beginTransaction() {
        if (threadConnection.get() == null) {
            Connection connection = getConnection();
            threadConnection.set(connection);
            try {
                threadConnection.get().setAutoCommit(false);
                return threadConnection.get();
            } catch (SQLException exception) {
                logger.error("Error in beginTransaction", exception);
            }
        }
        return null;
    }

    public void rollBackTransaction() {
        try {
            threadConnection.get().rollback();
            threadConnection.get().setAutoCommit(true);
            threadConnection.get().close();
            threadConnection.remove();
        } catch (SQLException exception) {
            logger.error("Error in rollBackTransaction", exception);
        }
    }


    public void endTransaction() {
        try {
            threadConnection.get().commit();
            threadConnection.get().setAutoCommit(true);
            threadConnection.get().close();
            threadConnection.remove();
        } catch (SQLException exception) {
            logger.error("Error in endTransaction", exception);
        }
    }

}