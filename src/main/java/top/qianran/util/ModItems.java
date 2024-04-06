package top.qianran.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.armor.GoblinArmorItem;
import top.qianran.armor.material.GoblinArmorMaterial;
import top.qianran.entity.ModEntity;
import top.qianran.items.CopyItem;
import top.qianran.items.GoblinItem;
import top.qianran.items.RedDiamond;
import top.qianran.tools.weapon.GoblinSword;
import top.qianran.tools.*;

public class ModItems {
    public static final Item GOBLIN_MEAT = ModItems.registerItem("goblin_meat", new Item(new Item.Settings()));
    //攻速默认4f，如果想设置3.2f的攻速，写-0.8f
    public static final Item GOBLIN_SWORD = ModItems.registerItem("goblin_sword", new GoblinSword(new GoblinToolMaterial(), 8, -0.8f, new FabricItemSettings().group(Registries.GOBLIN_GROUP)));
    public static final Item GOBLIN_AXE = ModItems.registerItem("goblin_axe", new GoblinAxe(new GoblinToolMaterial(), 10, -1.6f, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final Item GOBLIN_HOE = ModItems.registerItem("goblin_hoe", new GoblinHoe(new GoblinToolMaterial(), 1, -3.0f, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final Item GOBLIN_PICKAXE = ModItems.registerItem("goblin_pickaxe", new GoblinPickaxe(new GoblinToolMaterial(), 5, -2.8f, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final Item GOBLIN_SHOVEL = ModItems.registerItem("goblin_shovel", new GoblinShovel(new GoblinToolMaterial(), 3, -3.0f, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    //盔甲
    public static final ArmorMaterial GOBLIN_ARMOR_MATERIAL = new GoblinArmorMaterial();//多态
    public static final ArmorItem GOBLIN_HELMET = ModItems.registerArmorItem("goblin_helmet",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_CHESTPLATE = ModItems.registerArmorItem("goblin_chestplate",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_LEGGINGS = ModItems.registerArmorItem("goblin_leggings",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(Registries.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_BOOTS = ModItems.registerArmorItem("goblin_boots",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(Registries.GOBLIN_GROUP)));

    public static final Item JB_ITEM = ModItems.registerItem("jb_item", new Item(new Item.Settings()));
    public static final Item TEST1 = ModItems.registerItem("test1", new GoblinItem(new Item.Settings(), "This is a test1 item"));
    public static final Item TEST2 = ModItems.registerItem("test2", new GoblinItem(new Item.Settings(), "This is a test2 item"));
    public static final Item TEST3 = ModItems.registerItem("test3", new GoblinItem(new Item.Settings(), "This is a test3 item"));

    public static final Item BOWL_OF_WATER = ModItems.registerItem("bowl_of_water", new BowItem(new Item.Settings()));
    public static final Item COPY_ITEM = ModItems.registerItem("copy_item", new CopyItem(new Item.Settings()));

    public static final Item SHIT_SPAWN_EGG = ModItems.registerItem("shit_spawn_egg", new SpawnEggItem(ModEntity.SHIT_ENTITY,0xA66D36,0x291C0C, new Item.Settings()));
    public static final Item SHITA_SPAWN_EGG = ModItems.registerItem("shita_spawn_egg", new SpawnEggItem(ModEntity.SHITA_ENTITY,0xA66D36,0x291C0C, new Item.Settings()));
    public static final Item GOBLIN_SPAWN_EGG = ModItems.registerItem("goblin_spawn_egg", new SpawnEggItem(ModEntity.GOBLIN_ENTITY, 0x70B319,0x302B11, new Item.Settings()));
    public static final Item RED_DIAMOND = ModItems.registerItem("red_diamond", new RedDiamond(new Item.Settings().group(TestGroup.TEST_GROUP)));
    public static void item(){
    }
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }
    private static ArmorItem registerArmorItem(String name, ArmorItem item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }

}
