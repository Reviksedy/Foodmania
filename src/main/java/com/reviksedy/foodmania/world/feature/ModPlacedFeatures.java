package com.reviksedy.foodmania.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

public class ModPlacedFeatures {

    public static final Holder<PlacedFeature> WILD_CORN_PLACED = PlacementUtils.register("wild_corn_placed",
            ModConfiguredFeatures.WILD_CORN, RarityFilter.onAverageOnceEvery(32),
            InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());

}