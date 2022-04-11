package com.shafaat.seisma.taxcalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.math.BigDecimal;

public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

//    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;

    @Min(value = 0)
    @Max(value = 2147483647)
    private Integer annualSalary;

    @NotNull
    @Min(value = 0)
    @Max(value = 11)
    private Integer paymentMonth;

    @DecimalMin(value = "0")
    @DecimalMax(value = "50")
    private BigDecimal superRate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(Integer annualSalary) {
        this.annualSalary = annualSalary;
    }

    public Integer getPaymentMonth() {
        return paymentMonth;
    }

    public void setPaymentMonth(Integer paymentMonth) {
        this.paymentMonth = paymentMonth;
    }

    public BigDecimal getSuperRate() {
        return superRate;
    }

    public void setSuperRate(BigDecimal superRate) {
        this.superRate = superRate;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", annualSalary=" + getAnnualSalary() +
            ", paymentMonth=" + getPaymentMonth() +
            ", superRate=" + getSuperRate() +
            "}";
    }
}
