package com.martirosov.tacocloud.repository;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("Ingredient_Ref")
public class IngredientRefs {
    private final String ingredient;
}
