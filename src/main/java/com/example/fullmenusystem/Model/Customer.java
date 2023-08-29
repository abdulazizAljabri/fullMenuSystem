package com.example.fullmenusystem.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "should not be null")
    @Column(columnDefinition = "double not null")
    private Double customerBalance;

    @NotEmpty(message = "should not be empty")
    @Column(columnDefinition = "varchar(10) NOT NULL unique")
    private String customerPhoneNumber;

    @OneToOne
    @JsonIgnore
    @MapsId
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy ="customer")
    private Set<Bill> bills;

}

