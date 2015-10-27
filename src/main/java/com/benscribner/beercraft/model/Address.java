package com.benscribner.beercraft.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.validator.constraints.Length;

/**
 * A class to represent an address
 */
@Embeddable
public class Address implements Serializable {

    private static final long serialVersionUID = 3617859655330969141L;

    private String address1;
    private String address2;
    private String address3;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    /**
     * The Address 1 field
     * 
     * @return the address 1 field
     */
    @Length(max = 150, message = "Max length is 150")
    @Column(name = "address1", length = 150)
    public String getAddress1() {
        return address1;
    }

    /**
     * The Address 2 field
     * 
     * @return the address 2 field
     */
    @Length(max = 150, message = "Max length is 150")
    @Column(name = "address2", length = 150)
    public String getAddress2() {
        return address2;
    }

    /**
     * The Address 3 field
     * 
     * @return the address 3 field
     */
    @Length(max = 150, message = "Max length is 150")
    @Column(name = "address3", length = 150)
    public String getAddress3() {
        return address3;
    }

    /**
     * The City field
     * 
     * @return the city field
     */
    @Length(max = 50, message = "Max length is 50")
    @Column(name = "city", length = 50)
    public String getCity() {
        return city;
    }

    /**
     * The Country field
     * 
     * @return the country field
     */
    @Length(max = 100, message = "Max length is 100")
    @Column(name = "country", length = 100)
    public String getCountry() {
        return country;
    }

    /**
     * The Postal Code field
     * 
     * @return the postal code field
     */
    @Length(max = 15, message = "Max length is 15")
    @Column(name = "postal_code", length = 15)
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * The State field
     * 
     * @return the state field
     */
    @Length(max = 100, message = "Max length is 100")
    @Column(name = "state", length = 100)
    public String getState() {
        return state;
    }

    /**
     * Set the Address 1 field
     * 
     * @param address1
     *            the field to set
     */
    public void setAddress1(final String address1) {
        this.address1 = address1;
    }

    /**
     * Set the Address 2 field
     * 
     * @param address2
     *            the field to set
     */
    public void setAddress2(final String address2) {
        this.address2 = address2;
    }

    /**
     * Set the Address 3 field
     * 
     * @param address3
     *            the field to set
     */
    public void setAddress3(final String address3) {
        this.address3 = address3;
    }

    /**
     * Set the City field
     * 
     * @param city
     *            the field to set
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Set the Country field
     * 
     * @param country
     *            the field to set
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * Set the Postal Code field
     * 
     * @param postal
     *            code the field to set
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Set the State field
     * 
     * @param state
     *            the field to set
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object o) {
        if (o != null && o.getClass() != this.getClass()) {
            return false;
        }

        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}