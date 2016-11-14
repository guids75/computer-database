package com.excilys.formation.computerdatabase.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;
import com.excilys.formation.computerdatabase.persistence.DaoInterface;
import com.excilys.formation.computerdatabase.util.PropertiesReader;

public class Page<T> {
  
  private static final String propFileName = "page.properties";
  private static int nbElementsByPage; //number of elements in one page
  private int actualPage; //the page opened
  private int nbPages; //the total number of pages
  
  static {
    int tmpNbElementsByPage = -1;
    try {
      tmpNbElementsByPage = Integer.valueOf(PropertiesReader.getInstance().getPropValues(propFileName).getProperty("nbElementsByPage")); 
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    nbElementsByPage = tmpNbElementsByPage;
  }
  
  public Page() { }
  
  public Page(int nbElements){
    actualPage = 1;
    nbPages = (int)Math.ceil(((float)nbElements)/nbElementsByPage);
  }

  public static int getNbElementsByPage() {
    return nbElementsByPage;
  }

  public static void setNbElementsByPage(int nbElementsByPage) {
    Page.nbElementsByPage = nbElementsByPage;
  }

  public int getActualPage() {
    return actualPage;
  }

  public void setActualPage(int actualPage) {
    this.actualPage = actualPage;
  }

  public int getNbPages() {
    return nbPages;
  }

  public void setNbPages(int nbPages) {
    this.nbPages = nbPages;
  }
  
  public boolean hasNext(){
    return (actualPage != nbPages);
  }
  
  public boolean hasPrev(){
    return (actualPage != 1);
  }
  

}
