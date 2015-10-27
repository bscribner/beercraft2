package com.benscribner.beercraft.model.impl;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;

import com.benscribner.beercraft.model.Role;
import com.benscribner.beercraft.model.User;

/**
 * File: PersonImpl Project: BeerCraft Author: @author Ben Scribner Version: @version
 * 1.0 Date: Purpose: @see com.benscribner.beercraft.model.User
 */
@Entity
@Table(name = "users")
public class UserImpl extends AbstractIdentifiableObject implements User {
    private static final long serialVersionUID = 3832626162173359411L;

    private String username;
    private String password;
    private String confirmPassword;
    private List<Role> roles = new ArrayList<Role>();
    private boolean enabled = true;
    private PersonImpl person;

    /**
     * Default constructor
     */
    public UserImpl() {

    }

    /**
     * Constructor that takes a username
     * 
     * @param username
     */
    public UserImpl(final String username) {
        this.username = username;
    }

    /**
     * @see com.benscribner.beercraft.model.User#getUsername()
     */
    @Override
    @NotEmpty(message = "Username is required")
    @Column(nullable = false, length = 50, unique = true)
    public String getUsername() {
        return username;
    }

    /**
     * @see com.benscribner.beercraft.model.User#getPassword()
     */
    @Override
    @NotEmpty(message = "Password is required")
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    /**
     * @see com.benscribner.beercraft.model.User#getConfirmPassword()
     */
    @Override
    @Transient
    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * @see com.benscribner.beercraft.model.User#getRoles()
     */
    @Override
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, targetEntity = RoleImpl.class)
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = @JoinColumn(name = "role_id"))
    public List<Role> getRoles() {
        return roles;
    }

    /**
     * @see com.benscribner.beercraft.model.User#addRole(com.benscribner.beercraft.model.Role)
     */
    @Override
    @Transient
    public void addRole(final Role role) {
        getRoles().add(role);
    }

    /**
     * @see com.benscribner.beercraft.model.User#isEnabled()
     */
    @Override
    @Column(name = "account_enabled")
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Override
    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new LinkedHashSet<GrantedAuthority>();
        authorities.addAll(roles);
        return authorities;
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
     */
    @Override
    @Transient
    public boolean isAccountNonExpired() {
        return isEnabled();
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    @Transient
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    /**
     * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
     */
    @Override
    @Transient
    public boolean isCredentialsNonExpired() {
        return isEnabled();
    }

    /**
     * @see com.benscribner.beercraft.model.User#getPerson()
     */
    @Override
    @Valid
    @OneToOne(cascade = { CascadeType.ALL })
    public PersonImpl getPerson() {
        return person;
    }

    /**
     * set the username
     * 
     * @param username
     *            the username to set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * set the password
     * 
     * @param password
     *            the password to set
     */
    public void setPassword(final String password) {
        this.password = password;
    }

    /**
     * set the confirm password
     * 
     * @param confirmPassword
     *            the confirm password to set
     */
    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    /**
     * set the roles
     * 
     * @param roles
     *            the roles to set
     */
    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    /**
     * set enabled
     * 
     * @param enabled
     *            is the account enabled or not
     */
    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * set the person
     * 
     * @param person
     *            the person to set
     */
    public void setPerson(final PersonImpl person) {
        this.person = person;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o != null && o.getClass() != this.getClass()) {
            return false;
        }

        return EqualsBuilder.reflectionEquals(this, o, new String[] { "confirmPassword", "roles" });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, new String[] { "confirmPassword", "roles" });
    }
}