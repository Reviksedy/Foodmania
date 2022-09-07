package com.reviksedy.foodmania.world;

import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.world.gen.ModCornGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Foodmania.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    public static void biomeLoadingEvent(final BiomeLoadingEvent event) {
        ModCornGeneration.generateCorn(event);
    }
}