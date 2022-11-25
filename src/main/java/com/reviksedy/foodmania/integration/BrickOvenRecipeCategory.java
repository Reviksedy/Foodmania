package com.reviksedy.foodmania.integration;

import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.block.ModBlocks;
import com.reviksedy.foodmania.recipe.BrickOvenRecipe;
import com.reviksedy.foodmania.screen.BrickOvenMenu;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import javax.annotation.Nonnull;

public class BrickOvenRecipeCategory implements IRecipeCategory<BrickOvenRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(Foodmania.MOD_ID, "baking");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(Foodmania.MOD_ID, "textures/gui/brick_oven_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public BrickOvenRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(ModBlocks.BRICK_OVEN.get()));
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends BrickOvenRecipe> getRecipeClass() {
        return BrickOvenRecipe.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Brick Oven");
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
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull BrickOvenRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 15).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 34).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.INPUT, 50, 53).addIngredients(Ingredient.of(Items.COAL)).addIngredients(Ingredient.of(Items.CHARCOAL));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 34).addItemStack(recipe.getResultItem());
    }
}