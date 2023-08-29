package com.example.fullmenusystem.Controller;

import com.example.fullmenusystem.Service.BillSerivce;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/bill")
public class BillController {
    private final BillSerivce billserivce;

    @GetMapping("/")
    public ResponseEntity getBill(){
        return ResponseEntity.status(HttpStatus.OK).body(billserivce.getAllOrders());
    }
}
