package top.qianran.world.feature;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightmapPlacementModifier;
import top.qianran.util.ModBlocks;

public class ModPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> MYSTERIOUS_TREE_CHECKED = PlacedFeatures.register("mysterious_placed",
            ModConfiguredFeatures.MYSTERIOUS_TREE_SPAWN, VegetationPlacedFeatures.modifiers(
                    // 一开始是生成的数量，然后是额外生成的概率，最后是额外生成的数量
                    PlacedFeatures.createCountExtraModifier(1,0.1f, 2)
            ));

    public static final RegistryEntry<PlacedFeature> RED_DIAMOND_ORE_UPPER_PLACED = PlacedFeatures.register("red_diamond_ore_upper_placed",
            ModConfiguredFeatures.RED_DIAMOND_ORE, ModOreFeatures.modifiersWithCount(90,  //矿石生成数量
                    HeightRangePlacementModifier.uniform(YOffset.fixed(0), YOffset.getTop())));//正态分布 0层~世界顶层

    public static final RegistryEntry<PlacedFeature> RED_DIAMOND_ORE_LOWER_PLACED = PlacedFeatures.register("red_diamond_ore_lower_placed",
            ModConfiguredFeatures.RED_DIAMOND_ORE, ModOreFeatures.modifiersWithCount(10,  //矿石生成数量
                    HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(0))));//正态分布 世界底层~0层
}
