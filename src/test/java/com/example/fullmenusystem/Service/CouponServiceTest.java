package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Repository.CouponRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {
    @InjectMocks
    CouponService couponService;

    @Mock
    CouponRepository couponRepository;
    Coupon coupon1;

    Coupon coupon2;

    List<Coupon> coupons;

    @BeforeEach
    void setup() {
        coupon1 = new Coupon(null,"abdc12",200.0,"active");
        coupon2 = new Coupon(null,"bdvfh123",250.0,"active");

        coupons = new ArrayList<>();
        coupons.add(coupon1);
        coupons.add(coupon2);
    }


    // 1 ServiceTest
    @Test
    public void getCouponsTest(){
        when(couponRepository.findAll()).thenReturn(coupons);

        List<Coupon> list = couponService.getCoupons();

        Assertions.assertEquals(list,coupons);

        verify(couponRepository,times(1)).findAll();
    }

    //2  ServiceTest
    @Test
    public void deleteTest(){
        when(couponRepository.findCouponById(coupon1.getId())).thenReturn(coupon1);

        couponService.removeCoupon(coupon1.getId());

        verify(couponRepository,times(1)).findCouponById(coupon1.getId());
        verify(couponRepository,times(1)).deleteById(coupon1.getId());
    }

    // 3 ServiceTest
    @Test
    public void updateTest(){
        when(couponRepository.findCouponById(coupon2.getId())).thenReturn(coupon2);

       couponService.update(coupon1.getId(),coupon1);

        verify(couponRepository,times(1)).findCouponById(coupon2.getId());
        verify(couponRepository,times(1)).save(coupon2);
    }
}
