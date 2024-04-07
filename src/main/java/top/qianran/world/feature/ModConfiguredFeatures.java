package top.qianran.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.lwjgl.system.macosx.MacOSXLibraryDL;
import top.qianran.util.ModBlocks;

import java.util.List;

public class ModConfiguredFeatures  {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MYSTERIOUS_TREE =
            ConfiguredFeatures.register("mysterious_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(ModBlocks.MYSTERIOUS_LOG),
                    //基础高度，第一次随机增加的高度，第二次随机增加的高度
                    new StraightTrunkPlacer(18, 5, 2),
                    BlockStateProvider.of(ModBlocks.MYSTERIOUS_LEAVES),
                    //树干上树叶半径, 树叶下树叶半径, 树叶高度
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 8),
                    new TwoLayersFeatureSize(1, 0, 2)//不知道是什么
            ).build());

    public static final RegistryEntry<PlacedFeature> MYSTERIOUS_TREE_CHECKED =
            PlacedFeatures.register("mysterious_tree_checked", ModConfiguredFeatures.MYSTERIOUS_TREE,
                    PlacedFeatures.wouldSurvive(ModBlocks.MYSTERIOUS_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> MYSTERIOUS_TREE_SPAWN =
            ConfiguredFeatures.register("mysterious_tree_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(ModConfiguredFeatures.MYSTERIOUS_TREE_CHECKED, 0.5f)),
                            ModConfiguredFeatures.MYSTERIOUS_TREE_CHECKED));

    public static void register() {

    }
}
