package com.excilys.formation.computerdatabase.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author GUIDS
 *
 */
@Entity
@Table(name="usercdb")
public class User implements Serializable {

    private static final long serialVersionUID = -2924131400187898081L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id; // the id of the company 

    @Column(name="login")
    private String login; // the id of the company
    
    @Column(name="password")
    private String password; // the name of the company

    @Column(name="enabled")
    private boolean enabled; //access
    
    @Enumerated(EnumType.STRING)
    @Column(name="userRole")
    private UserRole userRole;
    
    protected User() {
    }
    
    /**
     * Private constructor to use a builder.
     * 
     * @param builder
     *            : the builder of COmpany.
     */
    private User(Builder builder) {
        this.id = builder.id;
        this.login = builder.login;
        this.password = builder.password;
        this.enabled = builder.enabled;
        this.userRole = builder.userRole;
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
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return the current id of the company
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param id
     *            : the id to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the current name of the company
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param name
     *            : the name to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * @return the current name of the company
     */
    public UserRole getUseRole() {
        return userRole;
    }
    
    /**
     * @return the current name of the company
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }
    
    @Override
    public String toString() {
        return new StringBuilder("User [id=").append(id).append(", login=").append(login).append(", password=").append(password)
                .append(", enabled=").append(enabled).append(", userRole=").append(userRole).append("]").toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (enabled ? 1231 : 1237);
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((login == null) ? 0 : login.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
        result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (enabled != other.enabled)
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (login == null) {
            if (other.login != null)
                return false;
        } else if (!login.equals(other.login))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        if (userRole == null) {
            if (other.userRole != null)
                return false;
        } else if (!userRole.equals(other.userRole))
            return false;
        return true;
    }



    /**
     * The class of the Computer builder.
     * 
     * @author GUIDS
     */
    public static class Builder {
        private Long id;
        private String login;
        private String password;
        private boolean enabled;
        private UserRole userRole;

        /**
         * The builder of Computer.
         * 
         * @param name
         *            : the name of the computer
         */
        public Builder(String login) {
            this.login = login;
        }

        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public Builder id(Long id) {
            this.id = id;
            return this;
        }
        
        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        
        
        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public Builder enabled(boolean enabled) {
            this.enabled = enabled;
            return this;
        }
        
       
        /**
         * Setter for the id attribute.
         * 
         * @param id
         *            : the id to set
         * @return the ComputerBuilder with his id updated
         */
        public Builder userRole(UserRole userRole) {
            this.userRole = userRole;
            return this;
        }

        /**
         * Create the Computer according to the ComputerBuilder values.
         * 
         * @return the Computer based on its builder
         */
        public User build() {
            return new User(this);
        }

    }

}
