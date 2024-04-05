package top.qianran.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.blocks.ChangeBlock;
import top.qianran.blocks.NumberBlock;
import top.qianran.blocks.TextBoxBlock;

public class ModBlocks {
    public static void block(){
    }

    public static final Block RED_DIAMOND_BLOCK = ModBlocks.registerBlock("red_diamond_block",new Block(FabricBlockSettings.of(Material.METAL).strength(1f).requiresTool()));
    public static final Block TEST_BOX_BLOCK = ModBlocks.registerBlock("test_box_block",new TextBoxBlock(FabricBlockSettings.of(Material.STONE)));
    public static final Block CHANGE_BLOCK = ModBlocks.registerBlock("change_block", new ChangeBlock(FabricBlockSettings.of(Material.STONE)));
    public static final Block NUMBER_BLOCK = ModBlocks.registerBlock("number_block", new NumberBlock(FabricBlockSettings.of(Material.STONE).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, new Item.Settings()));
        return block;
    }

}
