package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Api.ApiResponse;
import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coupons")
public class CouponController {
    private final CouponService couponService;

    @GetMapping("/")
    public ResponseEntity getAllCoupons() {
        return ResponseEntity.status(HttpStatus.OK).body(couponService.getCoupons());
    }

    @PostMapping("/add")
    public ResponseEntity addCoupon(@RequestBody @Valid Coupon coupon) {
        couponService.addCoupon(coupon);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("added"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity removeCoupon(@PathVariable Integer id) {
        couponService.removeCoupon(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon deleted"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateCoupon(@PathVariable Integer id, @RequestBody @Valid Coupon coupon) {
        couponService.update(id, coupon);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon updated"));
    }

    @GetMapping("/endcoupons/{id}")
    public ResponseEntity endCoupons(@PathVariable Integer id) {
        couponService.endCoupons(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Coupon is ended"));
    }
}
