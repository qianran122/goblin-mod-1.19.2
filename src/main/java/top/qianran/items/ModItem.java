package top.qianran.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.entity.ModEntity;
//import top.qianran.entity.ModEntity;

public class ModItem {

    public static final Item SHIT_SPAWN_EGG = ModItem.registerItems("shit_spawn_egg", new SpawnEggItem(ModEntity.SHIT_ENTITY,0xA66D36,0x291C0C, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item GOBLIN_SPAWN_EGG = ModItem.registerItems("goblin_spawn_egg", new SpawnEggItem(ModEntity.GOBLIN_ENTITY, 0x70B319,0x302B11, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item JB_ITEM = ModItem.registerItems("jb_item", new Item(new Item.Settings().group(ItemGroup.MISC)));
    public static void item(){
    }
    private static Item registerItems(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }

}
