package com.shafaat.seisma.taxcalc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class PaySlip implements Serializable {

    private static final long serialVersionUID = 1L;


    @JsonIgnore
    private Long id; // Currently not in use.
    @JsonIgnore
    private LocalDate fromDateLocalDate;
    private String fromDate;
    @JsonIgnore
    private LocalDate toDateLocalDate;
    private String toDate;
    private BigDecimal grossIncome;
    private BigDecimal incomeTax;
    private BigDecimal superannuation;
    private Integer netIncome;
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFromDateLocalDate() {
        return fromDateLocalDate;
    }

    public void setFromDateLocalDate(LocalDate fromDateLocalDate) {
        this.fromDateLocalDate = fromDateLocalDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDateLocalDate() {
        return toDateLocalDate;
    }

    public void setToDateLocalDate(LocalDate toDateLocalDate) {
        this.toDateLocalDate = toDateLocalDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public BigDecimal getGrossIncome() {
        return grossIncome;
    }

    public void setGrossIncome(BigDecimal grossIncome) {
        this.grossIncome = grossIncome;
    }

    public BigDecimal getIncomeTax() {
        return incomeTax;
    }

    public void setIncomeTax(BigDecimal incomeTax) {
        this.incomeTax = incomeTax;
    }

    public BigDecimal getSuperannuation() {
        return superannuation;
    }

    public void setSuperannuation(BigDecimal superannuation) {
        this.superannuation = superannuation;
    }

    public Integer getNetIncome() {
        return netIncome;
    }

    public void setNetIncome(Integer netIncome) {
        this.netIncome = netIncome;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "PaySlip{" +
            "id=" + getId() +
            ", fromDateLocalDate='" + getFromDateLocalDate() + "'" +
            ", fromDate='" + getFromDate() + "'" +
            ", toDateLocalDate='" + getToDateLocalDate() + "'" +
            ", toDate='" + getToDate() + "'" +
            ", grossIncome=" + getGrossIncome() +
            ", incomeTax=" + getIncomeTax() +
            ", superannuation=" + getSuperannuation() +
            ", netIncome=" + getNetIncome() +
            ", employee=" + getEmployee() +
            "}";
    }
}
