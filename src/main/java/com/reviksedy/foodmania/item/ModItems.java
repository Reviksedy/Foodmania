package com.reviksedy.foodmania.item;

import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.block.ModBlocks;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Foodmania.MOD_ID);

    public static final RegistryObject<Item> BACON_AND_EGGS = ITEMS.register("bacon_and_eggs",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BACON_AND_EGGS)));

    public static final RegistryObject<Item> BACON = ITEMS.register("bacon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.BACON)));

    public static final RegistryObject<Item> COOKED_BACON = ITEMS.register("cooked_bacon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.COOKED_BACON)));

    public static final RegistryObject<Item> SUSHI_ROLL = ITEMS.register("sushi_roll",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.SUSHI_ROLL)));

    public static final RegistryObject<Item> EAR_OF_RICE = ITEMS.register("ear_of_rice",
            () -> new ItemNameBlockItem(ModBlocks.RICE_PLANT.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> RICE = ITEMS.register("rice",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> CORN = ITEMS.register("corn",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(ModFoods.CORN)));

    public static final RegistryObject<Item> CORN_SEEDS = ITEMS.register("corn_seeds",
            () -> new ItemNameBlockItem(ModBlocks.CORN_PLANT.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> RICE_BOWL = ITEMS.register("rice_bowl",
            () -> new BowlFoodItem((new Item.Properties()).stacksTo(1).tab(CreativeModeTab.TAB_FOOD).food(ModFoods.RICE_BOWL)));




    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}