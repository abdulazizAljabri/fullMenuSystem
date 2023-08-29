package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Api.ApiResponse;
import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/usecoupon/{couponCode}")
    public ResponseEntity useCoupon(@AuthenticationPrincipal User user, @PathVariable String couponCode) {
        customerService.useCoupon(user.getId(), couponCode);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("successfully used"));
    }

    @GetMapping("/buy/{productName}")
    public ResponseEntity buy(@AuthenticationPrincipal User user, @PathVariable String productName) {
        customerService.buyProduct(user.getUsername(), productName);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(" Done .. "));
    }
}
