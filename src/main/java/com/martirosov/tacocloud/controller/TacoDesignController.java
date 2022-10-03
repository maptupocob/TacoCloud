package com.martirosov.tacocloud.controller;


import com.martirosov.tacocloud.model.Ingredient;
import com.martirosov.tacocloud.model.Taco;
import com.martirosov.tacocloud.model.TacoOrder;
import com.martirosov.tacocloud.repository.IngredientsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.martirosov.tacocloud.model.Ingredient.Type;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Controller
@SessionAttributes("tacoOrder")
@RequestMapping("/design")
public class TacoDesignController {

    private final IngredientsRepository ingredientsRepository;

    public TacoDesignController(IngredientsRepository ingredientsRepository) {
        this.ingredientsRepository = ingredientsRepository;
    }

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = ingredientsRepository.findAll();
        Type[] types = Type.values();
        for (Type t : types) {
            model.addAttribute(t.toString().toLowerCase(), ingredients.stream().filter(i -> i.getType().equals(t)).collect(Collectors.toList()));
        }
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute("taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String design() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {
        if (errors.hasErrors()) {
            return "design";
        }
        taco.setCreatedAt(new Date());
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

}
