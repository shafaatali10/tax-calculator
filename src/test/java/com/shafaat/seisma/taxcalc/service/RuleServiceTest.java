package com.shafaat.seisma.taxcalc.service;

import com.shafaat.seisma.taxcalc.IntegrationTest;
import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import com.shafaat.seisma.taxcalc.domain.TaxSlab;
import com.shafaat.seisma.taxcalc.repository.TaxSlabRepository;
import com.shafaat.seisma.taxcalc.rules.ITaxRule;
import com.shafaat.seisma.taxcalc.rules.RuleEngine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
class RuleServiceTest {

    @Autowired
    RuleService ruleService;

    @Autowired
    TaxSlabRepository taxSlabRepository;

    @Autowired
    RuleEngine ruleEngine;

    @Test
    void checkRuleCountTest() {
        assertEquals(ruleEngine.getRulesCount(), taxSlabRepository.count());
    }

    @Test
    void testIncomeTaxPerMonthUpto18200(){
        TaxSlab taxSlab = new TaxSlab();
        taxSlab.setIncomeStart(0);
        taxSlab.setIncomeEnd(18200);
        taxSlab.setTaxOnEachDollar(BigDecimal.valueOf(0));
        taxSlab.setSlabAddition(0);

        int annualSalary = 17000;

        assertEquals(ruleService.calculateIncomeTaxForMonth(annualSalary,taxSlab), BigDecimal.valueOf(0));
    }

    @Test
    void testIncomeTaxPerMonth18201To37000(){
        TaxSlab taxSlab = new TaxSlab();
        taxSlab.setIncomeStart(18201);
        taxSlab.setIncomeEnd(37000);
        taxSlab.setTaxOnEachDollar(BigDecimal.valueOf(0.19));
        taxSlab.setSlabAddition(0);

        int annualSalary = 27000;

        assertEquals(ruleService.calculateIncomeTaxForMonth(annualSalary,taxSlab),
                BigDecimal.valueOf(139));
    }

    @Test
    void testIncomeTaxPerMonth37001To87000(){
        TaxSlab taxSlab = new TaxSlab();
        taxSlab.setIncomeStart(37001);
        taxSlab.setIncomeEnd(87000);
        taxSlab.setTaxOnEachDollar(BigDecimal.valueOf(0.325));
        taxSlab.setSlabAddition(3572);

        int annualSalary = 60050;

        assertEquals(ruleService.calculateIncomeTaxForMonth(annualSalary,taxSlab),
                BigDecimal.valueOf(922));
    }

    @Test
    void testIncomeTaxPerMonth87001To180000(){
        TaxSlab taxSlab = new TaxSlab();
        taxSlab.setIncomeStart(87001);
        taxSlab.setIncomeEnd(180000);
        taxSlab.setTaxOnEachDollar(BigDecimal.valueOf(0.37));
        taxSlab.setSlabAddition(19822);

        int annualSalary = 180000;

        assertEquals(ruleService.calculateIncomeTaxForMonth(annualSalary,taxSlab),
                BigDecimal.valueOf(4519));
    }

    @Test
    void testIncomeTaxPerMonthAbove180000(){
        TaxSlab taxSlab = new TaxSlab();
        taxSlab.setIncomeStart(180000);
        taxSlab.setIncomeEnd(Integer.MAX_VALUE);
        taxSlab.setTaxOnEachDollar(BigDecimal.valueOf(0.45));
        taxSlab.setSlabAddition(54232);

        int annualSalary = 200000;

        assertEquals(ruleService.calculateIncomeTaxForMonth(annualSalary,taxSlab),
                BigDecimal.valueOf(5269));
    }

}