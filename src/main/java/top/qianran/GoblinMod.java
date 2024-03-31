package top.qianran;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GoblinMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("goblin-mod");
	public static final Item EXAMPLE_ITEM = new Item(new Item.Settings().group(ItemGroup.MISC));

	public static final VerticalSlabBlock POLISHED_ANDESITE_VERTICAL_SLAB = Registry.register(
			Registry.BLOCK,
			new Identifier("goblin-mod", "polished_andesite_vertical_slab"),
			new VerticalSlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier("goblin-mod", "example_item"), EXAMPLE_ITEM);
		Registry.register(Registry.ITEM, new Identifier("goblin-mod", "polished_andesite_vertical_slab"),
				new BlockItem(POLISHED_ANDESITE_VERTICAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}
}