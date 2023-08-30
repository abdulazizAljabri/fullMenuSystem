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
@Entity
@NoArgsConstructor
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;
    @NotEmpty(message = "should be not  empty")
    @Column(columnDefinition = "varchar(10) NOT NULL unique")
    private String productName;
    @Column(columnDefinition = "varchar(15) NOT NULL check(category = 'drinks' or category = 'meals' or category = 'sweets')")
    @NotEmpty(message = "should be not empty")
    private String category;
    @Column(columnDefinition = "int ")
    @NotNull(message = "should be not empty")
    private Integer productCount;

    @NotNull(message = "should be not null")
    @Column(columnDefinition = "int not null")
    private Double productPrice;

}
