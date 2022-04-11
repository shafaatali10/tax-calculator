package com.shafaat.seisma.taxcalc.service;

import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import com.shafaat.seisma.taxcalc.domain.TaxSlab;
import com.shafaat.seisma.taxcalc.repository.TaxSlabRepository;
import com.shafaat.seisma.taxcalc.rules.ITaxRule;
import com.shafaat.seisma.taxcalc.rules.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class RuleService {

    @Autowired
    private TaxSlabRepository taxSlabRepository;

    @Autowired
    private RuleEngine ruleEngine;

    @PostConstruct
    public void registerRules(){

        List<TaxSlab> taxSlabs = taxSlabRepository.findAll();

        taxSlabs.stream().forEach( taxSlab -> {

            ITaxRule<Employee, PaySlip> rule = new ITaxRule<Employee, PaySlip>() {
                @Override
                public boolean doesFallInRange(Employee employee) {
                    return employee.getAnnualSalary() >= taxSlab.getIncomeStart() && employee.getAnnualSalary()  <= taxSlab.getIncomeEnd();
                }

                @Override
                public PaySlip process(Employee employee) {
                    PaySlip paySlip = new PaySlip();
                    paySlip.setEmployee(employee);

                    LocalDate firstDay = LocalDate.of(LocalDate.now().getYear(), employee.getPaymentMonth(), 1);
                    LocalDate lastDay = firstDay.with(TemporalAdjusters.lastDayOfMonth());

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM");

                    paySlip.setFromDateLocalDate(firstDay);
                    paySlip.setFromDate(firstDay.format(formatter));

                    paySlip.setToDateLocalDate(lastDay);
                    paySlip.setToDate(lastDay.format(formatter));

                    // Calculate Pay
                    BigDecimal grossSalary = BigDecimal.valueOf(employee.getAnnualSalary() / 12);
                    paySlip.setGrossIncome(grossSalary);

                    BigDecimal incomeTax = BigDecimal.valueOf(employee.getAnnualSalary() - (taxSlab.getIncomeStart() - 1))
                            .multiply(taxSlab.getTaxOnEachDollar())
                            .add(BigDecimal.valueOf(taxSlab.getSlabAddition()))
                            .divide(BigDecimal.valueOf(12), 0, RoundingMode.HALF_UP);

                    BigDecimal superannuation
                            = grossSalary.multiply(employee.getSuperRate()).setScale(0, RoundingMode.HALF_UP);

                    paySlip.setIncomeTax( incomeTax );
                    paySlip.setSuperannuation( superannuation );
                    paySlip.setNetIncome( grossSalary.subtract( incomeTax ).intValue() );
                    return paySlip;
                }
            };

            ruleEngine.registerRule(rule);
        });

    }

}
