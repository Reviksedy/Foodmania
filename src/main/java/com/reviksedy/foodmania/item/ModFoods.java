package com.reviksedy.foodmania.item;

import net.minecraft.world.food.FoodProperties;



public class ModFoods {
    public static final FoodProperties BACON_AND_EGGS = (new FoodProperties.Builder()).nutrition(9).saturationMod(1.2F).build();

    public static final FoodProperties BACON = (new FoodProperties.Builder()).nutrition(1).saturationMod(0.4F).build();

    public static final FoodProperties COOKED_BACON = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.8F).build();

    public static final FoodProperties SUSHI_ROLL = (new FoodProperties.Builder()).nutrition(7).saturationMod(1.0F).build();
    public static final FoodProperties CORN = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build();

    public static final FoodProperties RICE_BOWL = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.8F).build();
}