package com.example.fullmenusystem.Repository;

import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Repository.CouponRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CouponRepositoryTest {

    @Autowired
    CouponRepository couponRepository;

    Coupon coupon1;

    Coupon coupon2;


    @BeforeEach
    void setUp() {
        coupon1 = new Coupon(null,"abdc12",200.0,"active");
        coupon2 = new Coupon(null,"bdvfh123",250.0,"active");
    }

    // 1 RepositoryTest
    @Test
    void findCouponByIdTest(){
      couponRepository.save(coupon1);
      Coupon coupon =  couponRepository.findCouponById(coupon1.getId());
        Assertions.assertThat(coupon.getId()).isEqualTo(coupon1.getId());
    }

    // 2 RepositoryTest
    @Test
    void findCouponByCouponCodeTest(){
        couponRepository.save(coupon1);
        Coupon coupon = couponRepository.findCouponByCouponCode(coupon1.getCouponCode());
        Assertions.assertThat(coupon.getCouponCode()).isEqualTo(coupon1.getCouponCode());
    }

}
