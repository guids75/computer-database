package com.excilys.formation.computerdatabase.mapper;

import com.excilys.formation.computerdatabase.util.PropertiesReader;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class LocalDateMapper {

  private static final String propFileName = "date.properties";
  private static LocalDateMapper localDateMapper = new LocalDateMapper();

  private static final DateTimeFormatter dateTimeFormatter;
  private static final SimpleDateFormat simpleDateFormat;

  static {
    SimpleDateFormat tmpSimpleDateFormat = null;
    DateTimeFormatter tmpDateTimeFormatter = null;
    try {
      tmpSimpleDateFormat = new SimpleDateFormat(PropertiesReader.getInstance()
          .getPropValues(propFileName).getProperty("format"));
      tmpDateTimeFormatter = DateTimeFormatter.ofPattern(PropertiesReader.getInstance()
          .getPropValues(propFileName).getProperty("format"));
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    simpleDateFormat = tmpSimpleDateFormat;
    dateTimeFormatter = tmpDateTimeFormatter;
  }

  private LocalDateMapper() {
  }

  public static LocalDateMapper getInstance() {
    return localDateMapper;
  }

  /** Convert the dates to localDates.
   * 
   * @param date : date to convert
   * @return : the converted localDate
   */
  public LocalDate convertToLocalDate(Date date) {
    if (date != null){
      return LocalDate.parse(simpleDateFormat.format(date));
    } else {
      return null;
    }
  }

  /**
   * 
   * @param date
   * @return
   */
  public Timestamp convertToTimeStamp(LocalDate date) {
    String formattedDateTime = date.format(dateTimeFormatter);
    LocalDate lds = LocalDate.parse(formattedDateTime, dateTimeFormatter);
    return Timestamp.valueOf(lds.atStartOfDay());
  }

}
