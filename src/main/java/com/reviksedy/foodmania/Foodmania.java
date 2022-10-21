package com.reviksedy.foodmania;

import com.reviksedy.foodmania.block.ModBlocks;
import com.reviksedy.foodmania.block.entity.ModBlockEntities;
import com.reviksedy.foodmania.item.ModItems;
import com.mojang.logging.LogUtils;
import com.reviksedy.foodmania.recipe.ModRecipes;
import com.reviksedy.foodmania.screen.BrickOvenScreen;
import com.reviksedy.foodmania.screen.GrillScreen;
import com.reviksedy.foodmania.screen.ModMenuTypes;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import net.minecraftforge.eventbus.api.IEventBus;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Foodmania.MOD_ID)
public class Foodmania
{
    public static final String MOD_ID = "foodmania";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Foodmania()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        ModItems.register(eventBus);
        ModBlocks.register(eventBus);

        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);

        ModRecipes.register(eventBus);


        eventBus.addListener(this::clientSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.CORN_PLANT.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.WILD_CORN.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.GRILL.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.BRICK_OVEN.get(), RenderType.translucent());
        MenuScreens.register(ModMenuTypes.GRILL_MENU.get(), GrillScreen::new);
        MenuScreens.register(ModMenuTypes.BRICK_OVEN_MENU.get(), BrickOvenScreen::new);

    }

    private void setup(final FMLCommonSetupEvent event)
    {
        LOGGER.info("HEY BRO");
        LOGGER.info("CHECK OUT THIS BEDROCK BLOCK >> {}", Blocks.BEDROCK.getRegistryName());
    }
}
