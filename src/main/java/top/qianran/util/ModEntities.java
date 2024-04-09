package top.qianran.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.entity.custom.*;

public class ModEntities {

    public static final EntityType<GoblinEntity> GOBLIN_ENTITY = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblin-mod","goblin"),
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, GoblinEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f,1.5f))
                    .build()
    );

    public static void putEntityToBiomes(){
        /*  生物群系对照表（来自Minecraft wiki）
         *  虚空	            the_void	                0
         *  平原      	    plains	                    1
         *  向日葵平原	    sunflower_plains	        2
         *  白雪皑皑的平原	    snowy_plains	            3
         *  冰钉	            ice_spikes      	        4
         *  沙漠	            desert	                    5
         *  沼泽	            swamp	                    6
         *  红树林沼泽	    mangrove_swamp	            7
         *  森林	            forest	                    8
         *  花林	            flower_forest	            9
         *  白桦林	        birch_forest	            10
         *  黑暗森林    	    dark_forest	                11
         *  老白桦林	        old_growth_birch_forest	    12
         *  老松针叶林	    old_growth_pine_taiga	    13
         *  老生长云杉针叶林	old_growth_spruce_taiga	    14
         *  泰加林	        taiga	                    15
         *  白雪皑皑的针叶林	snowy_taiga	                16
         *
         *  稀树草原	        savanna	                    17
         *  稀树草原高原	    savanna_plateau	            18
         *  Windswept Hills（风吹山丘）	windswept_hills	19
         *  被风吹拂的砾石山	windswept_gravelly_hills	20
         *  风吹拂森林	    windswept_forest	        21
         *  风吹草树草原	    windswept_savanna	        22
         *
         *  丛林	            jungle	                    23
         *  稀疏的丛林	    sparse_jungle	            24
         *  竹林	            bamboo_jungle	            25
         *  荒地	            badlands	                26
         *  被侵蚀的荒地	        eroded_badlands	        27
         *  树木繁茂的荒地	    wooded_badlands	            28
         *  草 甸	        meadow	                    29
         *  樱桃林	        cherry_grove	            30
         *  格罗夫	        grove	                    31
         *  白雪皑皑的斜坡	    snowy_slopes	            32
         *  冰冻山峰	        frozen_peaks	            33
         *  锯齿状的山峰	    jagged_peaks	            34
         *  石峰	            stony_peaks	                35
         *  河	            river	                    36
         *  冰冻的河流	    frozen_river	            37
         *  海滩	            beach	                    38
         *  白雪皑皑的海滩 	snowy_beach	                39
         *  石岸	            stony_shore	                40
         *
         *  温暖的海洋	    warm_ocean	                41
         *  不冷不热的海洋	    lukewarm_ocean	            42
         *  深邃的温热海洋	    deep_lukewarm_ocean	        43
         *  海洋	            ocean	                    44
         *  深海	            deep_ocean	                45
         *  冷海	            cold_ocean	                46
         *  深冷的海洋	    deep_cold_ocean	            47
         *  冰冻的海洋	    frozen_ocean	            48
         *  深冰冻的海洋	    deep_frozen_ocean	        49
         *  蘑菇田	        mushroom_fields	            50
         *  滴水石洞	        dripstone_caves	            51
         *  郁郁葱葱的洞穴	    lush_caves	                52
         *  深邃的黑暗	    deep_dark	                53
         *  下界荒原	        nether_wastes	            54
         *  扭曲的森林	    warped_forest	            55
         *  绯红森林	        crimson_forest	            56
         *  灵魂沙谷	        soul_sand_valley	        57
         *  玄武岩三角洲	    basalt_deltas	            58
         *  结束	            the_end	                    59
         *  结束高地	        end_highlands	            60
         *  结束米德兰兹	    end_midlands	            61
         *  小端岛	        small_end_islands	        62
         *  终结贫瘠	        end_barrens	                63
         */

        /*
        BiomeSelectors可用选项（来自copilot，可能有误）
        all(): 匹配所有生物群系。
        builtIn(): 匹配在代码中定义的生物群系，而不是在数据包中定义的生物群系。
        vanilla(): 匹配所有来自Minecraft命名空间的生物群系。
        foundInOverworld(): 匹配所有在Overworld中生成的生物群系。
        foundInTheNether(): 匹配所有在Nether中生成的生物群系。
        foundInTheEnd(): 匹配所有在End中生成的生物群系。
        tag(TagKey<Biome> tag): 匹配所有在给定标签中的生物群系。
        excludeByKey(RegistryKey<Biome>... keys): 排除具有给定键的生物群系。
        includeByKey(RegistryKey<Biome>... keys): 仅选择具有给定键的生物群系。
        spawnsOneOf(EntityType<?>... entityTypes): 匹配在其中可以生成给定实体类型的生物群系。
        */
        //将实体添加到生物群系中
        BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, ModEntities.GOBLIN_ENTITY, 1, 5, 50);
    }
}
