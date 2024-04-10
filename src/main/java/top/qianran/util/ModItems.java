package top.qianran.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.armor.GoblinArmorItem;
import top.qianran.armor.material.GoblinArmorMaterial;
import top.qianran.items.*;
import top.qianran.tools.weapon.GoblinSword;
import top.qianran.tools.*;

public class ModItems {

    public static final Item JB_ITEM = ModItems.registerItem("jb_item", new Item(new Item.Settings()));
    public static final Item TEST1 = ModItems.registerItem("test1", new GoblinItem(new Item.Settings(), "This is a test1 item"));
    public static final Item TEST2 = ModItems.registerItem("test2", new GoblinItem(new Item.Settings(), "This is a test2 item"));
    public static final Item TEST3 = ModItems.registerItem("test3", new GoblinItem(new Item.Settings(), "This is a test3 item"));

    public static final Item BOWL_OF_WATER = ModItems.registerItem("bowl_of_water", new BowItem(new Item.Settings()));
    public static final Item COPY_ITEM = ModItems.registerItem("copy_item", new CopyItem(new Item.Settings()));
    public static final Item FINISH_BOOK = ModItems.registerItem("finish_book", new FinishBookItem(new Item.Settings()));
    public static final Item SILK_BAG = ModItems.registerItem("silk_bag", new SilkBagItem(new Item.Settings()));


    public static void item(){
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }
    private static ArmorItem registerArmorItem(String name, ArmorItem item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }

}
