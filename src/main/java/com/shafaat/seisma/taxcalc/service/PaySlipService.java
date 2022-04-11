package com.shafaat.seisma.taxcalc.service;

import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import com.shafaat.seisma.taxcalc.rules.RuleEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaySlipService {

    @Autowired
    private RuleEngine ruleEngine;

    public List<PaySlip> generateMonthlyPaySlip(List<Employee> employees) {
        List<PaySlip> paySlips = new ArrayList<>();
        for(Employee employee: employees){
            paySlips.add(ruleEngine.executeRulesAndGeneratePaySlip(employee));
        }
        return paySlips;
    }
}
