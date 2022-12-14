package com.reviksedy.foodmania.integration;

import com.reviksedy.foodmania.recipe.BrickOvenRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.recipe.GrillRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIFoodmaniaPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(Foodmania.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                GrillRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
        registration.addRecipeCategories(new
                BrickOvenRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<GrillRecipe> Grecipes = rm.getAllRecipesFor(GrillRecipe.Type.INSTANCE);
        List<BrickOvenRecipe> Brecipes = rm.getAllRecipesFor(BrickOvenRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(GrillRecipeCategory.UID, GrillRecipe.class),  Grecipes);
        registration.addRecipes(new RecipeType<>(BrickOvenRecipeCategory.UID, BrickOvenRecipe.class), Brecipes);
    }
}