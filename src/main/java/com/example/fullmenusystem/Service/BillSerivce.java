package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Model.Bill;
import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Model.Menu;
import com.example.fullmenusystem.Repository.BillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BillSerivce {
    private final BillRepository billRepository;

    public List<Bill> getAllOrders(){
        return billRepository.findAll();
    }

    public void addBill(Customer customer, Menu menu){
      Bill bill = new Bill(null,customer.getCustomerPhoneNumber(),menu.getCategory(),menu.getProductName(),menu.getProductPrice(),customer,menu);
      billRepository.save(bill);
    }

    public Bill findOrderUserById(Integer id) {
        return billRepository.findBillById(id);
    }
}
