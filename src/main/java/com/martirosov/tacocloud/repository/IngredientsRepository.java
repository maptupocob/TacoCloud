package com.martirosov.tacocloud.repository;

import com.martirosov.tacocloud.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface IngredientsRepository extends CrudRepository<Ingredient, String> {
    List<Ingredient> findAll();
}
