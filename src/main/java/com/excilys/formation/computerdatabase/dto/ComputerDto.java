package com.excilys.formation.computerdatabase.dto;

/**
 * @author GUIDS
 *
 */
public class ComputerDto {

  private long id; // id of the computer, required
  private String name; // name of the computer, required
  private String introduced; // date when the computer was introduced, optional
  private String discontinued; // date when the computer was discontinued,
                               // optional
  private long companyId; // id of the company which produces the computer,
                          // required
  private String companyName; // name of the company which produces the
                              // computer, required

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public long getCompanyId() {
    return companyId;
  }

  public void setCompanyId(long companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return new StringBuilder("Computer [id=").append(id).append(", name=").append(name).append(", introduced=")
        .append(introduced).append(", discontinued=").append(discontinued).append(", companyId=").append(companyId)
        .append(", companyName=").append(companyName).append("]").toString();
  }

}
