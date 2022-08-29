package com.martirosov.tacocloud.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TacoOrder implements Serializable {
    public static final long serialVersionUID = 1L;

    private long id;
    private Date placedAt;
    private String deliveryAddress;
    private String deliveryName;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        tacos.add(taco);
    }

}
