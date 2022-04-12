package com.shafaat.seisma.taxcalc.controller;

import com.shafaat.seisma.taxcalc.IntegrationTest;
import com.shafaat.seisma.taxcalc.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@IntegrationTest
class PaySlipControllerTest {

    @Autowired
    private MockMvc restMockMvc;

    @Test
    void testGenerateMonthlyPaySlip() throws Exception{

        Employee davidRudd = new Employee();
        davidRudd.setFirstName("David");
        davidRudd.setLastName("Rudd");
        davidRudd.setAnnualSalary(60050);
        davidRudd.setPaymentMonth(1);
        davidRudd.setSuperRate(BigDecimal.valueOf(0.09));

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = post("/api/payslip/monthly");
        request.content(TestUtil.convertObjectToJsonBytes(Arrays.asList(davidRudd)));
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        restMockMvc
                .perform(request)
                .andExpect(status().isOk())
                ;
    }

    @Test
    void testNegativeSalary() throws Exception{

        Employee davidRudd = new Employee();
        davidRudd.setFirstName("David");
        davidRudd.setLastName("Rudd");
        davidRudd.setAnnualSalary(-100);
        davidRudd.setPaymentMonth(1);
        davidRudd.setSuperRate(BigDecimal.valueOf(0.09));

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = post("/api/payslip/monthly");
        request.content(TestUtil.convertObjectToJsonBytes(Arrays.asList(davidRudd)));
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        restMockMvc
                .perform(request)
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void testInvalidMonth() throws Exception{

        Employee davidRudd = new Employee();
        davidRudd.setFirstName("David");
        davidRudd.setLastName("Rudd");
        davidRudd.setAnnualSalary(60050);
        davidRudd.setPaymentMonth(13);
        davidRudd.setSuperRate(BigDecimal.valueOf(0.09));

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = post("/api/payslip/monthly");
        request.content(TestUtil.convertObjectToJsonBytes(Arrays.asList(davidRudd)));
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        restMockMvc
                .perform(request)
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void testSuperGreaterThan50() throws Exception{

        Employee davidRudd = new Employee();
        davidRudd.setFirstName("David");
        davidRudd.setLastName("Rudd");
        davidRudd.setAnnualSalary(60050);
        davidRudd.setPaymentMonth(1);
        davidRudd.setSuperRate(BigDecimal.valueOf(51));

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));
        MockHttpServletRequestBuilder request = post("/api/payslip/monthly");
        request.content(TestUtil.convertObjectToJsonBytes(Arrays.asList(davidRudd)));
        request.accept(MEDIA_TYPE_JSON_UTF8);
        request.contentType(MEDIA_TYPE_JSON_UTF8);

        restMockMvc
                .perform(request)
                .andExpect(status().isBadRequest())
        ;
    }

}