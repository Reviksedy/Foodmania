package com.reviksedy.foodmania.item;

import net.minecraft.world.food.FoodProperties;



public class ModFoods {

    public static final FoodProperties SUSHI_ROLL = (new FoodProperties.Builder()).nutrition(9).saturationMod(1.0F).build();
    public static final FoodProperties CORN = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.6F).build();
    public static final FoodProperties POPCORN = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.7F).build();
    public static final FoodProperties POPCORN_BOX = (new FoodProperties.Builder()).nutrition(8).saturationMod(1.0F).build();
}