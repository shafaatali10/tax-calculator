package com.shafaat.seisma.taxcalc.controller;

import com.shafaat.seisma.taxcalc.domain.Employee;
import com.shafaat.seisma.taxcalc.domain.PaySlip;
import com.shafaat.seisma.taxcalc.service.PaySlipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PaySlipController {

    private final Logger log = LoggerFactory.getLogger(PaySlipController.class);

    @Autowired
    private PaySlipService paySlipService;

    @PostMapping("/payslip/monthly")
    public ResponseEntity<List<PaySlip>> generateMonthlyPaySlip(@Valid @RequestBody List<Employee> employees) {
        return ResponseEntity
                .ok()
                .body(paySlipService.generateMonthlyPaySlip(employees));
    }

}
