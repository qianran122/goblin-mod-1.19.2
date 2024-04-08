package top.qianran.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import top.qianran.world.feature.ModPlacedFeatures;

public class ModOreGeneration {
    public static void  ore(){
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.RED_DIAMOND_ORE_UPPER_PLACED.getKey().get());
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.RED_DIAMOND_ORE_LOWER_PLACED.getKey().get());

    }
}
