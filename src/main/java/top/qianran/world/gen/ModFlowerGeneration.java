package top.qianran.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import top.qianran.world.feature.ModPlacedFeatures;

public class ModFlowerGeneration {
    public static void flower() {
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.MYSTERIOUS_FLOWER_PLACED.getKey().get());
    }
}
