package com.excilys.formation.computerdatabase.dto;

/**
 * @author GUIDS
 *
 */
public class CompanyDto {

  private long id; // id of the company
  private String name; // name of the company

  /**
   * @return the current id of the company
   */
  public long getId() {
    return id;
  }

  /**
   * @param id
   *          : the id to set
   */
  public void setId(final long id) {
    this.id = id;
  }

  /**
   * @return the current name of the company
   */
  public String getName() {
    return name;
  }

  /**
   * @param name
   *          : the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return new StringBuilder("Company [id=").append(id).append(", name=").append(name).append("]")
        .toString();
  }
}
