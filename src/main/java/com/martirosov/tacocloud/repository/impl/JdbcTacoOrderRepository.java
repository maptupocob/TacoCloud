package com.martirosov.tacocloud.repository.impl;

import com.martirosov.tacocloud.model.Ingredient;
import com.martirosov.tacocloud.model.Taco;
import com.martirosov.tacocloud.model.TacoOrder;
import com.martirosov.tacocloud.repository.TacoOrderRepository;
import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTacoOrderRepository implements TacoOrderRepository {

    private JdbcOperations jdbcOperations;

    @Override
    @Transactional
    public TacoOrder save(TacoOrder tacoOrder) {
        tacoOrder.setPlacedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO Taco_Order " +
                "(delivery_name, delivery_address, cc_number, cc_expiration, cc_cvv, placed_at) VALUES (?, ?, ?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                tacoOrder.getDeliveryName(), tacoOrder.getDeliveryAddress(),
                tacoOrder.getCcNumber(), tacoOrder.getCcExpiration(), tacoOrder.getCcCVV(), tacoOrder.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        List<Taco> tacos = tacoOrder.getTacos();
        for (int i = 0; i < tacos.size(); i++) {
            saveTaco(tacos.get(i), orderId, i);
        }
        return tacoOrder;
    }

    private void saveTaco(Taco taco, long orderId, int order_key) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory("INSERT INTO Taco (name, taco_order, taco_order_key, created_at) VALUES (?, ?)",
                Types.VARCHAR, Type.LONG, Type.LONG, Types.TIMESTAMP);
        pscf.setReturnGeneratedKeys(true);
        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(taco.getName(), orderId, order_key, taco.getCreatedAt()));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        saveIngredientRefs(tacoId, taco.getIngredients());
    }

    //TODO здесь может быть проблема с ingredient(в книге ingredientRef)
    private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
        int i = 0;
        for (Ingredient ing : ingredients) {
            jdbcOperations.update("INSERT INTO Ingredient_Ref (ingredient, taco, taco_key)", ing, tacoId, i++);
        }
    }

    @Override
    public Optional<TacoOrder> findById(long id) {
        return Optional.empty();
    }
}
