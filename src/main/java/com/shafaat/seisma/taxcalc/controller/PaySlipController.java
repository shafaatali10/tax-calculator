package com.shafaat.seisma.taxcalc.controller;

import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import com.shafaat.seisma.taxcalc.service.PaySlipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class PaySlipController {

    private final Logger log = LoggerFactory.getLogger(PaySlipController.class);

    @Autowired
    private PaySlipService paySlipService;

    @PostMapping("/payslip/monthly")
    public ResponseEntity<List<PaySlip>> generateMonthlyPaySlip(@RequestBody List<Employee> employees) {


        boolean requestInvalid = isRequestInvalid(employees);
        if(requestInvalid){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity
                .ok()
                .body(paySlipService.generateMonthlyPaySlip(employees));
    }

    private boolean isRequestInvalid(List<Employee> employees){

        boolean isInvalid = false;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        for(Employee employee: employees){
            Set<ConstraintViolation<Employee>> violations = validator.validate(employee);

            for (ConstraintViolation<Employee> violation : violations) {
                isInvalid = true;
                log.error(violation.getMessage());
            }
        }
        return isInvalid;
    }


}
