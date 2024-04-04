package top.qianran.util;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {
    public static void block(){
    }

    private static Block registerBlock(String name, Block block){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, new Item.Settings()));
        return block;
    }

}
