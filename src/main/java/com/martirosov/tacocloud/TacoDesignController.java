package com.martirosov.tacocloud;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.martirosov.tacocloud.Ingredient.Type;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
public class TacoDesignController {

    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("FLTO", "Flour Tortilla" , Type.WRAP),
                new Ingredient("COTO", "Corn Tortilla", Type.WRAP),
                new Ingredient("GRBF", "Ground Beef", Type.PROTEIN),
                new Ingredient("CARN", "Carnitas", Type.PROTEIN),
                new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES),
                new Ingredient("LETC", "Lettuce", Type.VEGGIES),
                new Ingredient("CHED", "Cheddar", Type.CHEESE),
                new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
                new Ingredient("SLSA", "Salsa", Type.SAUCE),
                new Ingredient("SRCR", "Sour Cream", Type.SAUCE));

        Type[] types = Type.values();
        for (Type t: types) {
            model.addAttribute(t.toString().toLowerCase(), ingredients.stream().filter(i -> i.getType().equals(t)).collect(Collectors.toList()));
        }
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder(){
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String design(){
        return "design";
    }

}
