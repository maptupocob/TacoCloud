package com.martirosov.tacocloud;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {
    private String id;
    private String deliveryAddress;
    private String deliveryName;
    private String cardNumber;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

}
