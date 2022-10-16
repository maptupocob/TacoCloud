package com.martirosov.tacocloud.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class TacoOrder implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private Date placedAt;
    @NotBlank(message = "Address is required")
    private String deliveryAddress;
    @NotBlank(message = "Name is required")
    private String deliveryName;
//    @CreditCardNumber(message = "Not a valid credit card number")
    private String ccNumber;
//    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message = "Must be formatted MM/YY")
    private String ccExpiration;
//    @Digits(integer = 3, fraction = 0, message = "Invalid CVV")
    private String ccCVV;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

}
