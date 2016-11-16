package com.excilys.formation.computerdatabase.model;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import com.excilys.formation.computerdatabase.persistence.CompanyDao;
import com.excilys.formation.computerdatabase.persistence.ComputerDao;
import com.excilys.formation.computerdatabase.persistence.DaoInterface;
import com.excilys.formation.computerdatabase.util.PropertiesReader;

/**
 * @author GUIDS
 *
 * @param <T> : the object stored in the pages
 */
public class Page<T> {

  private static final String PROP_FILE_NAME = "page.properties"; ////the file to manage the properties of the pages
  private static int nbElementsByPage; //number of elements in one page
  private int actualPage; //the page opened
  private int nbPages; //the total number of pages

  static {
    int tmpNbElementsByPage = -1;
    //add the number of elements per page in the class
    try {
      tmpNbElementsByPage = Integer.valueOf(PropertiesReader.getInstance().getPropValues(PROP_FILE_NAME).getProperty("nbElementsByPage")); 
    } catch (IOException exception) {
      exception.printStackTrace();
    }
    nbElementsByPage = tmpNbElementsByPage;
  }

  public Page() { }

  /** Calculate the number of pages needed to display nbElements elements,
   * according to the number of elements per page.
   * 
   * @param nbElements : the total number of elements to display
   */
  public Page(int nbElements) {
    actualPage = 1;
    nbPages = (int)Math.ceil(((float)nbElements) / nbElementsByPage);
  }

  /**
   * @return the number of elements per page
   */
  public static int getNbElementsByPage() {
    return nbElementsByPage;
  }

  /**
   * @param nbElementsByPage : the number of elements per page to set
   */
  public static void setNbElementsByPage(int nbElementsByPage) {
    Page.nbElementsByPage = nbElementsByPage;
  }

  /**
   * @return the current page
   */
  public int getActualPage() {
    return actualPage;
  }

  /**
   * @param actualPage : the actual page to set
   */
  public void setActualPage(int actualPage) {
    this.actualPage = actualPage;
  }

  /**
   * @return the number of pages
   */
  public int getNbPages() {
    return nbPages;
  }

  /**
   * @param nbPages : number of pages to set
   */
  public void setNbPages(int nbPages) {
    this.nbPages = nbPages;
  }

  /** Check if the actual page has a following one.
   * 
   * @return a boolean corresponding to the existence of a following page (true if exists)
   */
  public boolean hasNext() {
    return (actualPage != nbPages);
  }

  /** Check if the actual page has a previous one.
   * 
   * @return a boolean corresponding to the existence of a previous page (true if exists)
   */
  public boolean hasPrev() {
    return (actualPage != 1);
  }


}
