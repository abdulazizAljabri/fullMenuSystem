package com.example.fullmenusystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
@Entity
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "should be not empty")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String PhoneNumber;
    @NotEmpty(message = "should be not empty")
    @Column(columnDefinition = "varchar(10) not null ")
    private String categoryName;
    @NotEmpty(message = "should be not empty")
    @Column(columnDefinition = "varchar(10) not null")
    private String productName;
    @NotNull(message = "should be not null")
    @Column(columnDefinition = "double not null ")
    private Double productPrice;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JoinColumn()
    @JsonIgnore
    private Menu menu;
}
