package top.qianran.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.blocks.*;
import top.qianran.blocks.breakBlock.BreakBlock;
import top.qianran.blocks.breakBlock.BreakEntity;
import top.qianran.blocks.containerBlock.ContainerBlock;
import top.qianran.blocks.containerBlock.ContainerEntity;
import top.qianran.blocks.exampleRecipesBlock.ExampleRecipesBlock;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlock;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlockEntity;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlockScreenHandler;
import top.qianran.blocks.newBoxBlock.NewBoxBlock;
import top.qianran.blocks.newBoxBlock.NewBoxEntity;
import top.qianran.blocks.uiBlock.UIBlock;
import top.qianran.blocks.uiBlock.UIBlockEntity;
import top.qianran.blocks.uiBlock.UIBlockScreenHandler;

public class ModBlocks {
    public static void block(){
    }

    public static final Block MYSTERIOUS_LOG = ModBlocks.registerBlock("mysterious_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)));
    public static final Block MYSTERIOUS_WOOD = ModBlocks.registerBlock("mysterious_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)));
    public static final Block MYSTERIOUS_LOG_STRIPPED = ModBlocks.registerBlock("mysterious_log_stripped", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)));
    public static final Block MYSTERIOUS_WOOD_STRIPPED = ModBlocks.registerBlock("mysterious_wood_stripped", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)));
    public static final Block MYSTERIOUS_PLANKS = ModBlocks.registerBlock("mysterious_planks", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)));

    public static final Block RED_DIAMOND_BLOCK = ModBlocks.registerBlock("red_diamond_block",new Block(AbstractBlock.Settings.of(Material.STONE).strength(2.0f).requiresTool()));
    public static final Block RED_DIAMOND_ORE = ModBlocks.registerBlock("red_diamond_ore",new Block(AbstractBlock.Settings.of(Material.STONE).strength(2.0f).requiresTool()));
    public static final Block TEST_BOX_BLOCK = ModBlocks.registerBlock("test_box_block",new TextBoxBlock(FabricBlockSettings.of(Material.STONE)));
    public static final Block CHANGE_BLOCK = ModBlocks.registerBlock("change_block", new ChangeBlock(FabricBlockSettings.of(Material.STONE)));
    public static final Block NUMBER_BLOCK = ModBlocks.registerBlock("number_block", new NumberBlock(FabricBlockSettings.of(Material.STONE).nonOpaque()));
    //带方块实体的方块
    public static final Block BREAK_BLOCK= ModBlocks.registerBlock("break_block",new BreakBlock(AbstractBlock.Settings.of(Material.STONE)));
    public static BlockEntityType<BreakEntity> BREAK_ENTITY = ModBlocks.registerBlockEntity("break_entity", FabricBlockEntityTypeBuilder.create(BreakEntity::new, BREAK_BLOCK).build(null));
    public static final Block CONTAINER_BLOCK = ModBlocks.registerBlock("container_block", new ContainerBlock(FabricBlockSettings.of(Material.STONE)));
    public static BlockEntityType<ContainerEntity> CONTAINER_ENTITY = ModBlocks.registerBlockEntity("container_entity", FabricBlockEntityTypeBuilder.create(ContainerEntity::new, CONTAINER_BLOCK).build(null));
    public static final Block NEW_BOX_BLOCK = ModBlocks.registerBlock("new_box_block", new NewBoxBlock(FabricBlockSettings.of(Material.STONE)));
    public static BlockEntityType<NewBoxEntity> NEW_BOX_ENTITY = ModBlocks.registerBlockEntity("new_box_entity", FabricBlockEntityTypeBuilder.create(NewBoxEntity::new, NEW_BOX_BLOCK).build(null));

    public static final Block UI_BLOCK = ModBlocks.registerBlock("ui_block", new UIBlock(FabricBlockSettings.of(Material.STONE)));
    public static BlockEntityType<UIBlockEntity> UI_BLOCK_ENTITY =
            ModBlocks.registerBlockEntity("ui_block_entity", FabricBlockEntityTypeBuilder.create(UIBlockEntity::new, UI_BLOCK).build(null));
    public static final ScreenHandlerType<UIBlockScreenHandler> UI_BLOCK_SCREEN_HANDLER;
    static {
        UI_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("goblin-mod", "ui_block"), UIBlockScreenHandler::new);
    }

    public static final Block FERMENTATION_VESSELS_BLOCK = ModBlocks.registerBlock("fermentation_vessels_block", new fermentationVesselsBlock(FabricBlockSettings.of(Material.STONE)));
    public static BlockEntityType<fermentationVesselsBlockEntity> FERMENTATION_VESSELS_BLOCK_ENTITY =
            ModBlocks.registerBlockEntity("fermentation_vessels_block_entity", FabricBlockEntityTypeBuilder.create(fermentationVesselsBlockEntity::new, FERMENTATION_VESSELS_BLOCK).build(null));
    public static final ScreenHandlerType<fermentationVesselsBlockScreenHandler> FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER;
    static {
        FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("goblin-mod","fermentation_vessels_block"), fermentationVesselsBlockScreenHandler::new);
    }
    //
    public static final Block EXAMPLE_RECIPES_BLOCK = ModBlocks.registerBlock("example_recipes_block", new ExampleRecipesBlock(FabricBlockSettings.of(Material.STONE)));
    //注册方块和对应的物品
    private static Block registerBlock(String name, Block block){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, new Item.Settings()));
        return block;
    }

    //注册方块和对应的物品, 可以对物品进行设置
    private static Block registerBlock(String name, Block block, Item.Settings settings){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, settings));
        return block;
    }


    //注册方块实体
    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> blockEntityType){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("goblin-mod", name), blockEntityType);
    }
}
