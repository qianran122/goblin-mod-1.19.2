package top.qianran.world.feature;

import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.PineFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import top.qianran.items.group.GoblinGroupThings;

import java.util.List;

public class ModConfiguredFeatures  {

    public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> MYSTERIOUS_TREE =
            ConfiguredFeatures.register("mysterious_tree", Feature.TREE, new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(GoblinGroupThings.MYSTERIOUS_LOG),
                    //基础高度，第一次随机增加的高度，第二次随机增加的高度
                    //new StraightTrunkPlacer(7, 4, 2),
                    new ForkingTrunkPlacer(6, 2, 2),
                    BlockStateProvider.of(GoblinGroupThings.MYSTERIOUS_LEAVES),
                    //树干上树叶半径, 树叶下树叶半径, 树叶高度
                    //new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(3), 8),
                    new PineFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), ConstantIntProvider.create(3)),
                    new TwoLayersFeatureSize(1, 0, 2)//不知道是什么
            ).build());

    public static final RegistryEntry<PlacedFeature> MYSTERIOUS_TREE_CHECKED =
            PlacedFeatures.register("mysterious_tree_checked", ModConfiguredFeatures.MYSTERIOUS_TREE,
                    PlacedFeatures.wouldSurvive(GoblinGroupThings.MYSTERIOUS_SAPLING));

    public static final RegistryEntry<ConfiguredFeature<RandomFeatureConfig, ?>> MYSTERIOUS_TREE_SPAWN =
            ConfiguredFeatures.register("mysterious_tree_spawn", Feature.RANDOM_SELECTOR,
                    new RandomFeatureConfig(List.of(new RandomFeatureEntry(ModConfiguredFeatures.MYSTERIOUS_TREE_CHECKED, 0.5f)),
                            ModConfiguredFeatures.MYSTERIOUS_TREE_CHECKED));

    //将矿石加入到石头中（替换石头）
    public static final List<OreFeatureConfig.Target> OVERWORLD_RED_DIAMOND_ORE = List.of(
            OreFeatureConfig.createTarget(OreConfiguredFeatures.STONE_ORE_REPLACEABLES, GoblinGroupThings.RED_DIAMOND_ORE.getDefaultState()),
            OreFeatureConfig.createTarget(OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES, GoblinGroupThings.RED_DIAMOND_ORE_DEEPSLATE.getDefaultState())
    );
    public static final RegistryEntry<ConfiguredFeature<OreFeatureConfig,?>> RED_DIAMOND_ORE = ConfiguredFeatures.register(
            "red_diamond_ore", Feature.ORE, new OreFeatureConfig(OVERWORLD_RED_DIAMOND_ORE, 9)//生成矿石的数量
    );

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> MYSTERIOUS_FLOWER = ConfiguredFeatures.register(
            "mysterious_flower", Feature.FLOWER, new RandomPatchFeatureConfig(32, 6, 2, //尝试次数，x轴延伸，y轴延伸
                    PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK,
                            new SimpleBlockFeatureConfig(BlockStateProvider.of(GoblinGroupThings.MYSTERIOUS_FLOWER)))));



    public static void register() {

    }
}
