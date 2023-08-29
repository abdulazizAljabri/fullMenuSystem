package com.example.fullmenusystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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
    private String PhoneNumber;
    private String categoryName;
    private String productName;
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
