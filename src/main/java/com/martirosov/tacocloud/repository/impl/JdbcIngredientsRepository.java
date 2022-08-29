package com.martirosov.tacocloud.repository.impl;

import com.martirosov.tacocloud.model.Ingredient;
import com.martirosov.tacocloud.repository.IngredientsRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcIngredientsRepository implements IngredientsRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcIngredientsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Ingredient> findAll() {
        System.out.println("g");
        return jdbcTemplate.query("SELECT id, name, type FROM ingredient", this::mapResultSetToIngredient);
    }


    @Override
    public Optional<Ingredient> findById(String id) {
        List<Ingredient> res = jdbcTemplate.query("SELECT id, name, type FROM ingredient WHERE id = ?", this::mapResultSetToIngredient, id);

        return res.size() == 0 ?
                Optional.empty() :
                Optional.of(res.get(0));
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        jdbcTemplate.update("INSERT INTO ingredient (id, name, type) VALUES (?, ?, ?)",
                ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
        return ingredient;
    }


    private Ingredient mapResultSetToIngredient(ResultSet resultSet, int i) throws SQLException {
        return new Ingredient(resultSet.getString("id"),
                resultSet.getString("name"),
                Ingredient.Type.valueOf(resultSet.getString("type")));
    }
}
