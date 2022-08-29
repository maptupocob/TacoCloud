package com.martirosov.tacocloud.repository;

import com.martirosov.tacocloud.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientsRepository {
    List<Ingredient> findAll();
    Optional<Ingredient> findById(String id);
    Ingredient save(Ingredient ingredient);
}
