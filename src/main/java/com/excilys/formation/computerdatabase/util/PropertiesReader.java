package com.excilys.formation.computerdatabase.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author GUIDS
 *
 */
public class PropertiesReader {

  private static InputStream inputStream;
  private static PropertiesReader propertiesReader = new PropertiesReader(); //singleton of this class

  /**
 * Private constructor for singleton.
 */
private PropertiesReader() {
  }

  /**
 * @return the singleton corresponding to this class.
 */
public static PropertiesReader getInstance() {
    return propertiesReader;
  }

  /** Read a properties file and return its properties.
   * 
 * @param propFileName : the name of the properties file
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
        throw new FileNotFoundException("property file '" + propFileName 
            + "' not found in the classpath");
      }
    } catch (Exception exception) {
      System.out.println("Exception: " + exception);
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
