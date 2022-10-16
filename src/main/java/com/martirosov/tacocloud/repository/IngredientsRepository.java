package com.martirosov.tacocloud.repository;

import com.martirosov.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientsRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findAll();
}
