package top.qianran.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;
import top.qianran.world.feature.ModPlacedFeatures;

public class ModTreeGeneration {
    public static void tree(){
        //在所有生物群系中添加地物
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.VEGETAL_DECORATION,
                ModPlacedFeatures.MYSTERIOUS_TREE_CHECKED.getKey().get());
    }
}
