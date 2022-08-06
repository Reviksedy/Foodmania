package com.reviksedy.foodmania.recipe;

import com.reviksedy.foodmania.Foodmania;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Foodmania.MOD_ID);

    public static final RegistryObject<RecipeSerializer<GrillRecipe>> GRILLING_SERIALIZER =
            SERIALIZERS.register("grilling", () -> GrillRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PotRecipe>> POT_COOKING_SERIALIZER =
            SERIALIZERS.register("pot_cooking", () -> PotRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}