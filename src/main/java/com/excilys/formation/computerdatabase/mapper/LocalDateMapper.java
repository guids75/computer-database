package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.util.PropertiesReader;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author GUIDS
 *
 */
public class LocalDateMapper {

  private static final String PROP_FILE_NAME = "date.properties"; //the file to manage the properties of the dates
  private static LocalDateMapper localDateMapper = new LocalDateMapper(); //singleton of this class

  private static DateTimeFormatter dateTimeFormatter; //to convert date to timestamp
  private static SimpleDateFormat simpleDateFormat; //to convert localdate to date

  /**
   * Private constructor for a singleton.
   */
  private LocalDateMapper() {
    try {
      simpleDateFormat = new SimpleDateFormat(PropertiesReader.getInstance()
          .getPropValues(PROP_FILE_NAME).getProperty("format"));
      dateTimeFormatter = DateTimeFormatter.ofPattern(PropertiesReader.getInstance()
          .getPropValues(PROP_FILE_NAME).getProperty("format"));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  /**
   * @return the singleton corresponding to this class
   */
  public static LocalDateMapper getInstance() {
    return localDateMapper;
  }

  /** Convert the dates to localDates.
   * 
   * @param date : date to convert
   * @return the converted localDate
   */
  public LocalDate convertToLocalDate(Date date) {
    if (date != null) {
      return LocalDate.parse(simpleDateFormat.format(date));
    } else {
      return null;
    }
  }

  /** Convert the localDates to timestamps.
   * 
   * @param date : localDate to convert
   * @return the converted timestamp
   */
  public Timestamp convertToTimeStamp(LocalDate date) {
    if (date != null) {
      String formattedDateTime = date.format(dateTimeFormatter);
      LocalDate lds = LocalDate.parse(formattedDateTime, dateTimeFormatter);
      return Timestamp.valueOf(lds.atStartOfDay());
    }
    return null;
  }

}
