package com.excilys.formation.computerdatabase.persistence;

import java.util.List;

import com.excilys.formation.computerdatabase.pagination.Page;

public final class Constraints {

    private int limit = -1;
    private int offset = -1;
    private Page page; // TODO stay??
    private String search;
    private String orderBy;
    private List<Long> idList;
    private Long idCompany = -1L;

    private Constraints(ConstraintsBuilder builder) {
        this.limit = builder.limit;
        this.offset = builder.offset;
        this.page = builder.page;
        this.search = builder.search;
        this.orderBy = builder.orderBy;
        this.idList = builder.idList;
        this.idCompany = builder.idCompany;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<Long> getIdList() {
        return idList;
    }

    public void setIdList(List<Long> idList) {
        this.idList = idList;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public static class ConstraintsBuilder {
        private int limit;
        private int offset;
        private Page page; // TODO stay??
        private String search;
        private String orderBy;
        private List<Long> idList;
        private Long idCompany;

        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public ConstraintsBuilder limit(int limit) {
            this.limit = limit;
            return this;
        }

        /**
         * Setter for the introduced attribute.
         * 
         * @param introduced
         *            : the date to set
         * @return the ComputerBuilder with his introduced date updated
         */
        public ConstraintsBuilder offset(int offset) {
            this.offset = offset;
            return this;
        }

        /**
         * Setter for the discontinued attribute.
         * 
         * @param discontinued
         *            : the date to set
         * @return the ComputerBuilder with his discontinued date updated
         */
        public ConstraintsBuilder page(Page page) {
            this.page = page;
            return this;
        }

        /**
         * Setter for the company attribute.
         * 
         * @param company
         *            : the company to set
         * @return the ComputerBuilder with his company updated
         */
        public ConstraintsBuilder search(String search) {
            this.search = search;
            return this;
        }

        /**
         * Setter for the company attribute.
         * 
         * @param company
         *            : the company to set
         * @return the ComputerBuilder with his company updated
         */
        public ConstraintsBuilder orderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        /**
         * Setter for the company attribute.
         * 
         * @param company
         *            : the company to set
         * @return the ComputerBuilder with his company updated
         */
        public ConstraintsBuilder idList(List<Long> idList) {
            this.idList = idList;
            return this;
        }

        public ConstraintsBuilder idCompany(Long idCompany) {
            this.idCompany = idCompany;
            return this;
        }

        /**
         * Create the Computer according to the ComputerBuilder values.
         * 
         * @return the Computer based on its builder
         */
        public Constraints build() {
            return new Constraints(this);
        }

    }

}
