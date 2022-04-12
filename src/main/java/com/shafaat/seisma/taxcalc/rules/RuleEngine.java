package com.shafaat.seisma.taxcalc.rules;

import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RuleEngine {

    List<ITaxRule<Employee, PaySlip>> rules;

    public RuleEngine() {
        rules = new ArrayList<>();
    }

    public PaySlip executeRulesAndGeneratePaySlip(Employee employee) {

        return rules.stream().filter( rule -> rule.doesFallInRange(employee) )
                .map( rule -> rule.process(employee))
                .findFirst()
                .orElseThrow( () -> new RuntimeException("No matching tax slab found"));

    }


    public RuleEngine registerRule(ITaxRule<Employee, PaySlip> rule) {
        rules.add(rule);
        return this;
    }

    public RuleEngine clearRules() {
        rules = new ArrayList<>();
        return this;
    }

    public int getRulesCount(){
        if(rules != null){
            return rules.size();
        }
        return 0;
    }

}
