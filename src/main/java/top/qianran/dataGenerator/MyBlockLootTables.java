package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.data.server.BlockLootTableGenerator;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;
import top.qianran.items.group.GoblinGroupThings;

import java.util.function.BiConsumer;

class MyBlockLootTables extends SimpleFabricLootTableProvider {
    public MyBlockLootTables(FabricDataGenerator dataGenerator) {
        super(dataGenerator, LootContextTypes.BLOCK);
    }
    //public static final Identifier TEST_CHEST = new Identifier("tutorial", "chests/test_loot");
    @Override
    public void accept(BiConsumer<Identifier, LootTable.Builder> biConsumer) {
        biConsumer.accept(new Identifier("goblin-mod", "blocks/mysterious_flower"),
                BlockLootTableGenerator.drops(GoblinGroupThings.MYSTERIOUS_FLOWER, GoblinGroupThings.MYSTERIOUS_FLOWER,
                        ConstantLootNumberProvider.create(1.0F)));

    /*    biConsumer.accept(TEST_CHEST, LootTable.builder()
                .pool(LootPool.builder().rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(Items.DIAMOND)
                                .apply(SetCountLootFunction.builder(ConstantLootNumberProvider.create(1.0F)))
                                .with(ItemEntry.builder(Items.DIAMOND_SWORD)).apply(EnchantWithLevelsLootFunction.create(UniformLootNumberProvider.create(20.0F, 39.0F))))
                );
*/
    }

}