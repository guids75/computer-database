package com.excilys.formation.computerdatabase.model;

import com.excilys.formation.computerdatabase.model.Company;

import java.time.LocalDate;

/**
 * @author GUIDS
 *
 */
public class Computer {

  private int id; //id of the computer, required
  private String name; //name of the computer, required
  private LocalDate introduced; //date when the computer was introduced, optional
  private LocalDate discontinued; //date when the computer was discontinued, optional
  private Company company; //company which produces the computer, required

  /** Private constructor to use a builder.
   * 
   * @param builder : the builder of Computer.
   */
  private Computer(ComputerBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.introduced = builder.introduced;
    this.discontinued = builder.discontinued;
    this.company = builder.company;
  }

  /**
   * @return the current id of the computer
   */
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }

  /**
   * @return the current name of the computer
   */
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the current date when the computer was introduced
   */
  public LocalDate getIntroduced() {
    return introduced;
  }
  
  public void setIntroduced(LocalDate introduced) {
    this.introduced = introduced;
  }

  /**
   * @return the current date when the computer was discontinued
   */
  public LocalDate getDiscontinued() {
    return discontinued;
  }
  
  public void setDiscontinued(LocalDate discontinued) {
    this.discontinued = discontinued;
  }

  /**
   * @return the current company which produces the computer
   */
  public Company getCompany() {
    return company;
  }
  
  public void setCompany(Company company) {
    this.company = company;
  }

  @Override
  public String toString() {
    return new StringBuilder("Computer [id=").append(id).append(", name=").append(name)
        .append(", introduced=").append(introduced).append(", discontinued=").append(discontinued)
        .append(", company=").append(company).append("]").toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((company == null) ? 0 : company.hashCode());
    result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
    result = prime * result + id;
    result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
    ComputerBuilder other = (ComputerBuilder) obj;
    if (company == null) {
      if (other.company != null) {
        return false;
      }
    } else if (!company.equals(other.company)) {
      return false;
    }
    if (discontinued == null) {
      if (other.discontinued != null) {
        return false;
      }
    } else if (!discontinued.equals(other.discontinued)) {
      return false;
    }
    if (id != other.id) {
      return false;
    }
    if (introduced == null) {
      if (other.introduced != null) {
        return false;
      }
    } else if (!introduced.equals(other.introduced)) {
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
  public static class ComputerBuilder {
    private int id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;

    /** The builder of Computer.
     * 
     * @param name : the name of the computer
     */
    public ComputerBuilder(String name) {
      this.name = name;
    }

    /** Setter for the id attribute.
     * 
     * @param id : the id to set
     * @return the ComputerBuilder with his id updated
     */
    public ComputerBuilder id(int id) {
      this.id = id;
      return this;
    }

    
    /** Setter for the introduced attribute.
     * 
     * @param introduced : the date to set
     * @return the ComputerBuilder with his introduced date updated
     */
    public ComputerBuilder introduced(LocalDate introduced) {
      this.introduced = introduced;
      return this;
    }

    /** Setter for the discontinued attribute.
     * 
     * @param discontinued : the date to set
     * @return the ComputerBuilder with his discontinued date updated
     */
    public ComputerBuilder discontinued(LocalDate discontinued) {
      this.discontinued = discontinued;
      return this;
    }
    
    /** Setter for the company attribute.
     * 
     * @param company : the company to set
     * @return the ComputerBuilder with his company updated
     */
    public ComputerBuilder company(Company company) {
      this.company = company;
      return this;
    }


    /** Create the Computer according to the ComputerBuilder values.
     * 
     * @return the Computer based on its builder
     */
    public Computer build() {
      return new Computer(this);
    }

  }
}
