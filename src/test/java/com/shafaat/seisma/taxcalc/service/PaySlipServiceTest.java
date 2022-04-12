package com.shafaat.seisma.taxcalc.service;

import com.shafaat.seisma.taxcalc.IntegrationTest;
import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@IntegrationTest
class PaySlipServiceTest {

    @Autowired
    PaySlipService paySlipService;

    @Test
    void testGeneratePaySlip(){

        Employee davidRudd = new Employee();
        davidRudd.setFirstName("David");
        davidRudd.setLastName("Rudd");
        davidRudd.setAnnualSalary(60050);
        davidRudd.setPaymentMonth(1);
        davidRudd.setSuperRate(BigDecimal.valueOf(0.09));

        Employee ryanChen = new Employee();
        ryanChen.setFirstName("Ryan");
        ryanChen.setLastName("Chen");
        ryanChen.setAnnualSalary(120000);
        ryanChen.setPaymentMonth(1);
        ryanChen.setSuperRate(BigDecimal.valueOf(0.1));

        List<PaySlip> paySlips = paySlipService.generateMonthlyPaySlip(Arrays.asList(davidRudd, ryanChen));

        for(PaySlip paySlip: paySlips){
            if(paySlip.getEmployee().getFirstName().equals("David")){
                assertEquals(paySlip.getGrossIncome(), BigDecimal.valueOf(5004));
                assertEquals(paySlip.getIncomeTax(), BigDecimal.valueOf(922));
                assertEquals(paySlip.getSuperannuation(), BigDecimal.valueOf(450));
                assertEquals(paySlip.getNetIncome(), 4082);
            }else{
                assertEquals(paySlip.getGrossIncome(), BigDecimal.valueOf(10000));
                assertEquals(paySlip.getIncomeTax(), BigDecimal.valueOf(2669));
                assertEquals(paySlip.getSuperannuation(), BigDecimal.valueOf(1000));
                assertEquals(paySlip.getNetIncome(), 7331);
            }
        }

    }
}