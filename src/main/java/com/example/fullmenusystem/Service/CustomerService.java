package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Api.ApiException;
import com.example.fullmenusystem.Model.Coupon;
import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Repository.AuthRepository;
import com.example.fullmenusystem.Repository.CouponRepository;
import com.example.fullmenusystem.Repository.CustomerRepository;
import com.example.fullmenusystem.Repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CouponRepository couponRepository;
    private final MenuRepository menuRepository;
    private final AuthRepository authRepository;
    private final BillSerivce billSerivce;

    public void useCoupon(Integer customerId,String couponCode){
        Customer customer = customerRepository.findCustomerById(customerId);
        Coupon coupon = couponRepository.findCouponByCouponCode(couponCode);
        System.out.println(coupon);
        if(customer == null){
            throw new ApiException("Customer not found");
        }
        else if(coupon == null){
            throw new ApiException("Coupon not found");
        }
        else if(coupon.getStatus().equals("used")){
            throw new ApiException("Coupon already used");
        }
        else {
            double newBalance = customer.getCustomerBalance() + coupon.getCouponPrice();
            customer.setCustomerBalance(newBalance);
            coupon.setStatus("used");
            couponRepository.save(coupon);
            customerRepository.save(customer);
        }
    }

    public void buyProduct(String userName ,String productName){
        User user =authRepository.findUserByUsername(userName);
        Customer customers = customerRepository.findCustomerById(user.getId());
        if (customers == null){
            throw new ApiException("Customer not found");
        }
        Menu menus = menuRepository.findMenuByProductName(productName);
        if(menus == null){
            throw new ApiException("Product not found");
        }
        if(menus.getProductCount() == 0){
            throw new ApiException("Product out of Stock");
        }
        else if(customers.getCustomerBalance() >= menus.getProductPrice()){
            double newBalance = customers.getCustomerBalance() - menus.getProductPrice();
            Integer newCount = menus.getProductCount() - 1 ;
            customers.setCustomerBalance(newBalance);
            menus.setProductCount(newCount);
            menuRepository.save(menus);
        }
        billSerivce.addBill(customers, menus);

    }



}
