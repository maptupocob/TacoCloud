package com.martirosov.tacocloud.repository;

import com.martirosov.tacocloud.model.TacoOrder;

import java.util.Optional;

public interface TacoOrderRepository {
    TacoOrder save(TacoOrder tacoOrder);
    Optional<TacoOrder> findById(long id);
}
