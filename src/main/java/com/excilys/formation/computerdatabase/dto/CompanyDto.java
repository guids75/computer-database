package com.excilys.formation.computerdatabase.dto;

import com.excilys.formation.computerdatabase.model.Company;
import com.excilys.formation.computerdatabase.model.Company.CompanyBuilder;

/**
 * @author GUIDS
 *
 */
public class CompanyDto {

    private Long id; // id of the company
    private String name; // name of the company

    public CompanyDto() {
    }
    
    /**
     * Private constructor to use a builder.
     * 
     * @param builder
     *            : the builder of COmpany.
     */
    private CompanyDto(CompanyDtoBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }
    
    /**
     * @return the current id of the company
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            : the id to set
     */
    public void setId(final Long id) {
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
     *            : the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringBuilder("Company [id=").append(id).append(", name=").append(name).append("]").toString();
    }
    
    /**
     * The class of the Computer builder.
     * 
     * @author GUIDS
     */
    public static class CompanyDtoBuilder {
        private Long id;
        private String name;

        /**
         * The builder of Computer.
         * 
         * @param name
         *            : the name of the computer
         */
        public CompanyDtoBuilder(String name) {
            this.name = name;
        }

        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public CompanyDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Create the Computer according to the ComputerBuilder values.
         * 
         * @return the Computer based on its builder
         */
        public CompanyDto build() {
            return new CompanyDto(this);
        }

    }
}
