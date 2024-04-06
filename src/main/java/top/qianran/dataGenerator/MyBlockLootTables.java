package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;
import top.qianran.util.ModBlocks;
import top.qianran.util.ModItems;

import java.util.function.BiConsumer;

class MyBlockLootTables extends SimpleFabricLootTableProvider {
    public MyBlockLootTables(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }

    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        // The BlockLootTableGenerator class contains a behemoth of utility methods. Just take some time and go through the methods available to override.
        biConsumer.accept(new Identifier("goblin-mod", "red_diamond_ore"),
                BlockLootTableGenerator.drops(ModBlocks.RED_DIAMOND_ORE, ModItems.RED_DIAMOND, ConstantLootNumberProvider.create(1.0F)));

    }

}