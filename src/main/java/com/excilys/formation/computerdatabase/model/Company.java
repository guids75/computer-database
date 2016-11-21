package com.excilys.formation.computerdatabase.model;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

/**
 * @author GUIDS
 *
 */
public class Company {

  private int id; // the id of the company
  private String name; //the name of the company

  /** Private constructor to use a builder.
   * 
   * @param builder : the builder of COmpany.
   */
  private Company(CompanyBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
  }

  /**
   * @return the current id of the company
   */
  public int getId() {
    return id;
  }

  /**
   * @param id : the id to set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the current name of the company
   */
  public String getName() {
    return name;
  }

  /**
   * @param name : the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new StringBuilder("Company [id=").append(id).append(", name=").append(name).append("]").toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + id;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Company other = (Company) obj;
    if (id != other.id) {
      return false;
    }
    if (name == null) {
      if (other.name != null) {
        return false;
      }
    } else if (!name.equals(other.name)) {
      return false;
    }
    return true;
  }

  /** The class of the Computer builder.
   * 
   * @author GUIDS
   */
  public static class CompanyBuilder {
    private int id;
    private String name;
    /** The builder of Computer.
     * 
     * @param name : the name of the computer
     */
    public CompanyBuilder(String name) {
      this.name = name;
    }

    /** Setter for the id attribute.
     * 
     * @param id : the id to set
     * @return the ComputerBuilder with his id updated
     */
    public CompanyBuilder id(int id) {
      this.id = id;
      return this;
    }

    /** Create the Computer according to the ComputerBuilder values.
     * 
     * @return the Computer based on its builder
     */
    public Company build() {
      return new Company(this);
    }

  }

}
