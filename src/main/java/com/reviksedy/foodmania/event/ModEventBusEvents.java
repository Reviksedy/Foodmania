package com.reviksedy.foodmania.event;

import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.recipe.BrickOvenRecipe;
import com.reviksedy.foodmania.recipe.GrillRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Foodmania.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {

    @SubscribeEvent
    public static void registerRecipeTypes(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, GrillRecipe.Type.ID, GrillRecipe.Type.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, BrickOvenRecipe.Type.ID, BrickOvenRecipe.Type.INSTANCE);
    }

}