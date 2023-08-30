package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Api.ApiException;
import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {
    private final CouponRepository couponRepository;


    public List<Coupon> getCoupons() {
        return couponRepository.findAll();
    }

    public void addCoupon(Coupon coupon) {
        couponRepository.save(coupon);
    }

    public void removeCoupon(Integer id) {
        Coupon coupon = couponRepository.findCouponById(id);
        if (coupon == null) {
            throw new ApiException("Coupon not found");
        }
        couponRepository.deleteById(id);
    }


    public void update(Integer id, Coupon coupon) {
        Coupon coupons = couponRepository.findCouponById(id);
        if (coupons == null) {
            throw new ApiException("Coupon not found");
        }
        coupons.setStatus(coupon.getStatus());
        coupons.setCouponPrice(coupon.getCouponPrice());
        couponRepository.save(coupons);
    }

    public void endCoupons(Integer id) {
        Coupon coupons = couponRepository.findCouponById(id);
        if (coupons == null) {
            throw new ApiException("Coupon not found");
        }
        coupons.setStatus("used");
        couponRepository.save(coupons);
    }

}
