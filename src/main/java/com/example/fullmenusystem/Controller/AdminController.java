package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin")
public class AdminController {
    private final AdminService adminService;

    @GetMapping("/")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllUsers());
    }

    @GetMapping("/coupons")
    public ResponseEntity getAllCoupons() {
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllCoupons());
    }
}
