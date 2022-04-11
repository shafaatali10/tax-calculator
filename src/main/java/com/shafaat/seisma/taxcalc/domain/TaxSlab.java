package com.shafaat.seisma.taxcalc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


@Entity
@Table(name = "tax_rule")
public class TaxSlab implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "income_start")
    private Integer incomeStart;

    @Column(name = "income_end")
    private Integer incomeEnd;

    @Column(name = "tax_on_each_dollar", precision = 21, scale = 2)
    private BigDecimal taxOnEachDollar;

    @Column(name = "slab_addition")
    private Integer slabAddition;

    @Column(name = "is_active")
    private Boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIncomeStart() {
        return incomeStart;
    }

    public void setIncomeStart(Integer incomeStart) {
        this.incomeStart = incomeStart;
    }

    public Integer getIncomeEnd() {
        return incomeEnd;
    }

    public void setIncomeEnd(Integer incomeEnd) {
        this.incomeEnd = incomeEnd;
    }

    public BigDecimal getTaxOnEachDollar() {
        return taxOnEachDollar;
    }

    public void setTaxOnEachDollar(BigDecimal taxOnEachDollar) {
        this.taxOnEachDollar = taxOnEachDollar;
    }

    public Integer getSlabAddition() {
        return slabAddition;
    }

    public void setSlabAddition(Integer slabAddition) {
        this.slabAddition = slabAddition;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "TaxRule{" +
            "id=" + getId() +
            ", incomeStart=" + getIncomeStart() +
            ", incomeEnd=" + getIncomeEnd() +
            ", taxOnEachDollar=" + getTaxOnEachDollar() +
            ", slabAddition=" + getSlabAddition() +
            ", isActive=" + getActive() +
            "}";
    }
}
