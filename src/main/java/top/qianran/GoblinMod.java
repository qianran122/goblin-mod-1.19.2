package top.qianran;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
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

	//注册方块与方块实体
	public static final Block DEMO_BLOCK = Registry.register(
			Registry.BLOCK,
			new Identifier("goblin-mod","demo_block"),
			new Block(FabricBlockSettings.copyOf(Blocks.STONE))
		);
	public static final BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY = Registry.register(
			Registry.BLOCK_ENTITY_TYPE,
			new Identifier("goblin-mod", "demo_block_entity"),
			BlockEntityType.Builder.create(DemoBlockEntity::new, DEMO_BLOCK).build(null));

	//注册方块，物品和方块实体
	//创建容器方块
	public static final Block BOX_BLOCK;
	public static final BlockItem BOX_BLOCK_ITEM;
	public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;
	public static final String MOD_ID = "testmod";
	// 我们的大型箱子中不同部分的公共id
	public static final Identifier BOX = new Identifier(MOD_ID, "box_block");
	static {
		BOX_BLOCK = Registry.register(Registry.BLOCK, BOX, new BoxBlock(FabricBlockSettings.copyOf(Blocks.CHEST)));
		BOX_BLOCK_ITEM = Registry.register(Registry.ITEM, BOX, new BlockItem(BOX_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

		//The parameter of build at the very end is always null, do not worry about it
		// 1.17 之前
		//BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, BlockEntityType.Builder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
		// 在 1.17 使用 FabricBlockEntityTypeBuilder 而不是 BlockEntityType.Builder
		BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
	}
	public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
	static
	{
		// 我们在这里使用 registerSimple 因为实体不是 ExtendedScreenHandlerFactory
		// 而是 NamedScreenHandlerFactory.
		// 后面的教程中，你将会看到 ExtendedScreenHandlerFactory 能做什么！
		BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOX, BoxScreenHandler::new);
	}
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier("goblin-mod", "example_item"), EXAMPLE_ITEM);
		Registry.register(Registry.ITEM, new Identifier("goblin-mod", "polished_andesite_vertical_slab"),
				new BlockItem(POLISHED_ANDESITE_VERTICAL_SLAB, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

		//注册demo_block物品
		Registry.register(Registry.ITEM, new Identifier("goblin-mod","demo_block"),
				new BlockItem(DEMO_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	}
}