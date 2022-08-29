package com.martirosov.tacocloud;

import com.martirosov.tacocloud.model.Ingredient;
import com.martirosov.tacocloud.repository.IngredientsRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientsRepository ingredientsRepository;

    public IngredientByIdConverter(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @Override
    public Ingredient convert(String source) {
        Map<String, Ingredient> ingredientMap = Map.of("COTO", new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                "FLTO", new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                "GRBF", new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                "CARN", new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                "TMTO", new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                "LETC", new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                "CHED", new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                "JACK", new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                "SLSA", new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                "SRCR", new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        return ingredientsRepository.findById(source).orElse(null);
    }
}
