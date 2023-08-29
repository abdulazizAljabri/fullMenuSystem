package com.example.fullmenusystem.Service;

import com.example.fullmenusystem.Api.ApiException;
import com.example.fullmenusystem.Model.User;
import com.example.fullmenusystem.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
@RequiredArgsConstructor
@Service
public class MyUserDetailService  implements UserDetailsService{
private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByUsername(username);
        if(user == null){
            throw new ApiException("Wrong UserName Or Password");
        }
        return user;
    }
}
