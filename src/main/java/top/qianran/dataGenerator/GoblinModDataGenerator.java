package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.data.server.EntityLootTableGenerator;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import top.qianran.dataGenerator.MyBlockLootTables;
import top.qianran.entity.custom.GoblinEntity;
import top.qianran.util.ModBlocks;
import top.qianran.util.ModItems;

import java.util.function.BiConsumer;

public class GoblinModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		fabricDataGenerator.addProvider(MyRecipeGenerator::new);
		fabricDataGenerator.addProvider(MyBlockLootTables::new);
	}
}


