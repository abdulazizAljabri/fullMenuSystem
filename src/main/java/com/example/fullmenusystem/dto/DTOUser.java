package com.example.fullmenusystem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DTOUser {

    @NotEmpty(message = "should be not empty")
    private String username;
    @NotEmpty(message = "should be not empty")
    private String password;
    @NotEmpty(message = "should be not empty")
    private String role;
    @NotNull(message = "should not be null")
    private Double customerBalance;
    @NotEmpty(message = "should not be empty")
    private String customerPhoneNumber;
}
