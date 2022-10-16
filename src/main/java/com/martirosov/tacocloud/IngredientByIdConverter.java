package com.martirosov.tacocloud;

import com.martirosov.tacocloud.model.Ingredient;
import com.martirosov.tacocloud.repository.IngredientsRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientsRepository ingredientsRepository;

    public IngredientByIdConverter(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Ingredient convert(String source) {
        return ingredientsRepository.findById(source).orElse(null);
    }
}
