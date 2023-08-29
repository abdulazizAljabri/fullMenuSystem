package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Api.ApiException;
import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Repository.AuthRepository;
import com.example.fullmenusystem.Repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final AuthRepository authRepository;
    private final CouponRepository couponRepository;

    public void deleteUser(String userName) {
        User user = authRepository.findUserByUsername(userName);
        if(user == null){
            throw new ApiException(" user not found ");
        }
        authRepository.delete(user);
    }

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }
}
