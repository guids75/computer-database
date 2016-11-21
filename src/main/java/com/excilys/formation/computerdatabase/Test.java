package com.excilys.formation.computerdatabase;

import com.excilys.formation.computerdatabase.persistence.HikariConnectionPool;
import javax.sql.DataSource;

public class Test {

  public static void main(String args[]){
    HikariConnectionPool hc = new HikariConnectionPool();
    DataSource ds = hc.getDataSource();
    System.out.println(ds.toString());
  }
}
