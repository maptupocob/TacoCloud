package com.martirosov.tacocloud.repository;

import com.martirosov.tacocloud.model.TacoOrder;
import org.springframework.data.repository.CrudRepository;

public interface TacoOrderRepository extends CrudRepository<TacoOrder, Long> {
}
