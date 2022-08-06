package com.reviksedy.foodmania.integration;

import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.block.ModBlocks;
import com.reviksedy.foodmania.item.ModItems;
import com.reviksedy.foodmania.recipe.GrillRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class GrillRecipeCategory implements IRecipeCategory<GrillRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Foodmania.MOD_ID, "grilling");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Foodmania.MOD_ID, "textures/gui/grill_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public GrillRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.GRILL.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends GrillRecipe> getRecipeClass() {
        return GrillRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Grill");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GrillRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 47, 10).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 37, 29).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 57, 29).addIngredients(recipe.getIngredients().get(2));

        builder.addSlot(RecipeIngredientRole.INPUT, 47, 51).addIngredients(Ingredient.of(Items.COAL));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 109, 24).addItemStack(recipe.getResultItem());
    }
}