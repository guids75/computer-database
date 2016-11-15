package com.excilys.formation.computerdatabase.model;

import com.excilys.formation.computerdatabase.model.Company;

import java.time.LocalDate;

public class Computer {

  private final int id; //required
  private final String name; //required
  private final LocalDate introduced; //optional
  private final LocalDate discontinued; //optional
  private final Company company; //required

  private Computer(ComputerBuilder builder) {
    this.id = builder.id;
    this.name = builder.name;
    this.introduced = builder.introduced;
    this.discontinued = builder.discontinued;
    this.company = builder.company;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public LocalDate getIntroduced() {
    return introduced;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public Company getCompany() {
    return company;
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

  public static class ComputerBuilder {
    private final int id;
    private final String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private final Company company;

    /** The builder of Company.
     * 
     * @param id : the id of the computer
     * @param name : the name of the computer
     * @param company : the company of the computer
     */
    public ComputerBuilder(int id, String name, Company company) {
      this.id = id;
      this.name = name;
      this.company = company;
    }

    public ComputerBuilder introduced(LocalDate introduced) {
      this.introduced = introduced;
      return this;
    }

    public ComputerBuilder discontinued(LocalDate discontinued) {
      this.discontinued = discontinued;
      return this;
    }

    public Computer build() {
      return new Computer(this);
    }

  }
}
