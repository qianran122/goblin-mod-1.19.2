package top.qianran;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.bernie.geckolib3.GeckoLib;
import top.qianran.test.Test;
import top.qianran.util.ModEntities;
import top.qianran.entity.custom.CubeEntity;
import top.qianran.util.*;
import top.qianran.world.feature.ModConfiguredFeatures;
import top.qianran.world.gen.ModWorldGen;
//import top.qianran.util.Registries;

public class GoblinMod implements ModInitializer {




	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("goblin-mod");


	@Override
	public void onInitialize() {
		ModConfiguredFeatures.register();
		ModWorldGen.gen();
		ModEntities.putEntityToBiomes();

		//加载测试物品组
		TestGroup.init();
		Test.test();
		//注册
		Registries.init();
		GeckoLib.initialize();
		ModItems.item();
		ModBlocks.block();
		ModRecipes.init();
	}
}