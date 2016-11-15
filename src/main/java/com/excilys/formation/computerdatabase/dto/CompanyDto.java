package com.excilys.formation.computerdatabase.dto;

public class CompanyDto {
  
  private int id;
  private String name;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /*@Override
  public String toString() {
    return new StringBuilder("Company [id=").append(id).append(", name=").append(name).append("]").toString();
  }*/
}
