package com.reviksedy.foodmania.block.entity;

import com.reviksedy.foodmania.Foodmania;
import com.reviksedy.foodmania.block.ModBlocks;
import com.reviksedy.foodmania.block.entity.custom.GrillBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, Foodmania.MOD_ID);

    public static final RegistryObject<BlockEntityType<GrillBlockEntity>> GRILL_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("grill", () ->
                    BlockEntityType.Builder.of(GrillBlockEntity::new,
                            ModBlocks.GRILL.get()).build(null));



    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}