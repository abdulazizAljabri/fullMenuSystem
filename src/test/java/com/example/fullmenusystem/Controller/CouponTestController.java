package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Service.CouponService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

import static java.awt.AWTEventMulticaster.add;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = CouponController.class,excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CouponTestController {

    @MockBean
    CouponService couponService;

    @Autowired
    MockMvc mockMvc;

    Coupon coupon1;
    Coupon coupon2;

    List<Coupon> coupons;


    @BeforeEach
    void setup() {
        coupon1 = new Coupon(1,"ABC",200.0,"active");
        coupon2 = new Coupon(1,"ABC",200.0,"active");
        coupons = Arrays.asList(coupon1,coupon2);
    }

    @Test
    public void testGetAll()throws Exception {
        Mockito.when(couponService.getCoupons()).thenReturn(coupons);
        mockMvc.perform(get("/api/v1/coupons/"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("active"));
    }

    @Test
    public void testDeleteCoupons()throws Exception {
        mockMvc.perform(delete("/api/v1/coupons/delete/{id}",coupon1.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddCoupons()throws Exception {
        mockMvc.perform(post("/api/v1/coupons/add").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(coupon1)))
                .andExpect(status().isOk());
    }
}
