package top.qianran.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import top.qianran.util.ModBlocks;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> MYSTERIOUS_TREE_CHECKED = PlacedFeatures.register("mysterious_tree_checked",
            ModConfiguredFeatures.MYSTERIOUS_TREE_SPAWN, VegetationPlacedFeatures.modifiers(
                    // 一开始是生成的数量，然后是额外生成的概率，最后是额外生成的数量
                    PlacedFeatures.createCountExtraModifier(1,0.1f, 2)
            ));
}
