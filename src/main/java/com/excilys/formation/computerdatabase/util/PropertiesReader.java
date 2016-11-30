package com.excilys.formation.computerdatabase.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author GUIDS
 *
 */
public enum PropertiesReader {

    INSTANCE;
    private static InputStream inputStream;
    private static final Logger slf4jLogger = LoggerFactory.getLogger(PropertiesReader.class);

    /**
     * Read a properties file and return its properties.
     * 
     * @param propFileName
     *          : the name of the properties file
     * @return Properties : return the properties read
     * @throws IOException
     */
    public Properties getPropValues(String propFileName) throws IOException {

        Properties prop = new Properties();
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                slf4jLogger.error("property file '" + propFileName + "' not found in the classpath");
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }
        } catch (Exception exception) {
            slf4jLogger.error("Exception in Properties getPropValues : " + exception.getMessage());
        } finally {
            inputStream.close();
        }
        return prop;
    }

    public static InputStream getInputStream() {
        return inputStream;
    }

    public static void setInputStream(InputStream inputStream) {
        PropertiesReader.inputStream = inputStream;
    }

}
