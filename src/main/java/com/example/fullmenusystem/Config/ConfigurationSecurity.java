package com.example.fullmenusystem.Config;

import com.example.fullmenusystem.Service.MyUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfigurationSecurity {

    private final MyUserDetailService myUserDetailService;

    @Bean
    public DaoAuthenticationProvider DaoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetailService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authenticationProvider(DaoAuthenticationProvider())
                .authorizeHttpRequests()
                .requestMatchers("/api/v1/users/register").permitAll()
                .requestMatchers("/api/v1/admin/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/admin/coupons").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/admin/delete").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/coupons/").permitAll()
                .requestMatchers("/api/v1/coupons/endcoupons/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/coupons/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/coupons/update/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/coupons/add").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/menus/add").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/menus/delete/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/menus/update/{id}").hasAuthority("ADMIN")
                .requestMatchers("/api/v1/customers/usecoupon/{couponCode}").hasAuthority("USER")
                .requestMatchers("/api/v1/customers/buy/{productName}").hasAuthority("USER")
                .requestMatchers("/api/v1/bill/").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .logout().logoutUrl("/api/v1/auth/logout")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .httpBasic();

        return http.build();
    }
}
