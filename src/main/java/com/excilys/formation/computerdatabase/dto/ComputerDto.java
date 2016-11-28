package com.excilys.formation.computerdatabase.dto;

import java.time.LocalDate;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Computer;
import com.excilys.formation.computerdatabase.model.Computer.ComputerBuilder;

/**
 * @author GUIDS
 *
 */
public class ComputerDto {

    private Long id; // id of the computer, required
    private String name; // name of the computer, required
    private String introduced; // date when the computer was introduced,
                               // optional
    private String discontinued; // date when the computer was discontinued,
    // optional
    private Long companyId; // id of the company which produces the computer,
    // required
    private String companyName; // name of the company which produces the
    // computer, required

    public ComputerDto() {
    }
    
    /**
     * Private constructor to use a builder.
     * 
     * @param builder
     *            : the builder of Computer.
     */
    private ComputerDto(ComputerDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.introduced = builder.introduced;
        this.discontinued = builder.discontinued;
        this.companyId = builder.companyId;
        this.companyName = builder.companyName;
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
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
                .append(introduced).append(", discontinued=").append(discontinued).append(", companyId=")
                .append(companyId).append(", companyName=").append(companyName).append("]").toString();
    }

    /**
     * The class of the Computer builder.
     * 
     * @author GUIDS
     */
    public static class ComputerDtoBuilder {
        private Long id;
        private String name;
        private String introduced;
        private String discontinued;
        private Long companyId;
        private String companyName;

        /**
         * The builder of Computer.
         * 
         * @param name
         *            : the name of the computer
         */
        public ComputerDtoBuilder(String name) {
            if (name.trim().length() > 1) {
                this.name = name;
            } else {
                throw new IllegalArgumentException("The name of the computer must contain at least 2 characters");
            }
        }

        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public ComputerDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Setter for the introduced attribute.
         * 
         * @param introduced
         *            : the date to set
         * @return the ComputerBuilder with his introduced date updated
         */
        public ComputerDtoBuilder introduced(String introduced) {
            this.introduced = introduced;
            return this;
        }

        /**
         * Setter for the discontinued attribute.
         * 
         * @param discontinued
         *            : the date to set
         * @return the ComputerBuilder with his discontinued date updated
         */
        public ComputerDtoBuilder discontinued(String discontinued) {
            this.discontinued = discontinued;
            return this;
        }

        /**
         * Setter for the company attribute.
         * 
         * @param company
         *            : the company to set
         * @return the ComputerBuilder with his company updated
         */
        public ComputerDtoBuilder companyId(Long companyId) {
            this.companyId = companyId;
            return this;
        }
        
        /**
         * Setter for the company attribute.
         * 
         * @param company
         *            : the company to set
         * @return the ComputerBuilder with his company updated
         */
        public ComputerDtoBuilder companyName(String companyName) {
            this.companyName = companyName;
            return this;
        }

        /**
         * Create the Computer according to the ComputerBuilder values.
         * 
         * @return the Computer based on its builder
         */
        public ComputerDto build() {
            return new ComputerDto(this);
        }

    }
    
}
