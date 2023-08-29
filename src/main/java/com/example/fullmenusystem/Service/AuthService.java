package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Model.Customer;
import com.example.fullmenusystem.Controller.dto.DTOUser;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Repository.AuthRepository;
import com.example.fullmenusystem.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;

    public void register(DTOUser dtoUser) {
        String hash = new BCryptPasswordEncoder().encode(dtoUser.getPassword());
        User user = new User(null, dtoUser.getUsername(), hash, "USER", null);
        Customer customer = new Customer(null, dtoUser.getCustomerBalance(), dtoUser.getCustomerPhoneNumber(), user,null);
        customerRepository.save(customer);
    }

    public void updated(String userName, User user) {
        User user1 = authRepository.findUserByUsername(userName);
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        user1.setUsername(user.getUsername());
        user1.setPassword(hash);
        authRepository.save(user1);
    }

    public void deleted(String userName) {
        User user1 = authRepository.findUserByUsername(userName);
        authRepository.delete(user1);
    }

    public User getInfo(String userName) {
        User user1 = authRepository.findUserByUsername(userName);
        return user1;

    }

}
