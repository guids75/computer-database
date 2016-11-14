package com.excilys.formation.computerdatabase.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

  private static InputStream inputStream;

  private PropertiesReader() {
  }
  
  private static PropertiesReader propertiesReader = new PropertiesReader();
  
  public static PropertiesReader getInstance() {
    return propertiesReader;
  }
  
  /**
   * 
   * @return
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
    } catch (Exception e) {
      System.out.println("Exception: " + e);
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
