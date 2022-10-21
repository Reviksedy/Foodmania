package com.reviksedy.foodmania.block;

import com.reviksedy.foodmania.Foodmania;

import com.reviksedy.foodmania.block.custom.BrickOvenBlock;
import com.reviksedy.foodmania.block.custom.CornPlantBlock;
import com.reviksedy.foodmania.block.custom.GrillBlock;
import com.reviksedy.foodmania.item.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Foodmania.MOD_ID);


    public static final RegistryObject<Block> CORN_PLANT = registerBlockWithoutBlockItem("corn_plant",
            () -> new CornPlantBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT).noOcclusion()));


    public static final RegistryObject<Block> WILD_CORN = registerBlock("wild_corn",
            () -> new TallGrassBlock(BlockBehaviour.Properties.copy(Blocks.GRASS).noOcclusion()), CreativeModeTab.TAB_DECORATIONS);


	public static final RegistryObject<Block> SMOOTH_COBBLESTONE = registerBlock("smooth_cobblestone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).noOcclusion()), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> GRILL = registerBlock("grill",
            () -> new GrillBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion().strength(0.4f).requiresCorrectToolForDrops().lightLevel(
                    (state) -> state.getValue(GrillBlock.LIT) ? 12 : 0)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> BRICK_OVEN = registerBlock("brick_oven",
            () -> new BrickOvenBlock(BlockBehaviour.Properties.copy(Blocks.COBBLESTONE).noOcclusion().requiresCorrectToolForDrops().lightLevel(
                    (state) -> state.getValue(GrillBlock.LIT) ? 12 : 0)), CreativeModeTab.TAB_DECORATIONS);

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block,
                                                                     CreativeModeTab tab, String tooltipKey) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab, tooltipKey);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab, String tooltipKey) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)) {
            @Override
            public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltip, TooltipFlag pFlag) {
                pTooltip.add(new TranslatableComponent(tooltipKey));
            }
        });
    }



    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties().tab(tab)));
    }

 
    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}