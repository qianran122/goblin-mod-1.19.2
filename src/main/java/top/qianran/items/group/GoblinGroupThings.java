package top.qianran.items.group;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.Registry;
import top.qianran.armor.GoblinArmorItem;
import top.qianran.armor.material.GoblinArmorMaterial;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlock;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlockEntity;
import top.qianran.blocks.fermentationVesselsBlock.fermentationVesselsBlockScreenHandler;
import top.qianran.items.GoblinLeatherBagItem;
import top.qianran.items.RedDiamond;
import top.qianran.tools.*;
import top.qianran.tools.weapon.GoblinSword;
import top.qianran.util.ModEntities;
import top.qianran.world.feature.tree.ModMysteriousSaplingGenerator;

class GoblinGroup{
    //哥布林物品组
    public static final ItemGroup GOBLIN_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("goblin-mod","goblin_item"))
            .icon(() -> new ItemStack(GoblinGroupThings.GOBLIN_MEAT))
            .appendItems(stacks -> {
                //神秘木系列
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_LOG));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_WOOD));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_LOG_STRIPPED));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_WOOD_STRIPPED));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_PLANKS));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_LEAVES));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_SAPLING));
                stacks.add(new ItemStack(GoblinGroupThings.MYSTERIOUS_FLOWER));
                //发酵器
                stacks.add(new ItemStack(GoblinGroupThings.FERMENTATION_VESSELS_BLOCK));
                //哥布林
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_MEAT));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_SINEWS));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_LEATHER));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_LEATHER_BAG));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_INGOT));
                //武器与工具
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_SWORD));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_AXE));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_HOE));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_PICKAXE));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_SHOVEL));
                //盔甲
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_HELMET));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_CHESTPLATE));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_LEGGINGS));
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_BOOTS));

                //生成蛋
                stacks.add(new ItemStack(GoblinGroupThings.GOBLIN_SPAWN_EGG));
            })
            .build();
    public static void setGoblinGroup(){}

}
public class GoblinGroupThings {


    public static final Block MYSTERIOUS_LOG  = registerBlock("mysterious_log", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_WOOD = registerBlock("mysterious_wood", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_LOG_STRIPPED = registerBlock("mysterious_log_stripped", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_WOOD_STRIPPED = registerBlock("mysterious_wood_stripped", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_PLANKS = registerBlock("mysterious_planks", new PillarBlock(AbstractBlock.Settings.of(Material.WOOD).strength(4f)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_LEAVES = registerBlock("mysterious_leaves", new Block(FabricBlockSettings.copy(Blocks.OAK_LEAVES)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block MYSTERIOUS_SAPLING = registerBlock("mysterious_sapling", new SaplingBlock(new ModMysteriousSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    //炖菜buff
    public static final Block MYSTERIOUS_FLOWER = registerBlock("mysterious_flower", new FlowerBlock(StatusEffects.STRENGTH, 20, FabricBlockSettings.copy(Blocks.ORANGE_TULIP)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));

    public static final Item RED_DIAMOND = registerItem("red_diamond", new RedDiamond(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Block RED_DIAMOND_BLOCK = registerBlock("red_diamond_block",new Block(AbstractBlock.Settings.of(Material.STONE).strength(2.0f).requiresTool()), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));
    public static final Block RED_DIAMOND_ORE = registerBlock("red_diamond_ore",new OreBlock(FabricBlockSettings.copy(Blocks.IRON_ORE), UniformIntProvider.create(5, 10)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));//经验球掉落的值的范围
    public static final Block RED_DIAMOND_ORE_DEEPSLATE = registerBlock("red_diamond_ore_deepslate",new OreBlock(FabricBlockSettings.copy(Blocks.DEEPSLATE_DIAMOND_ORE), UniformIntProvider.create(5, 10)), new Item.Settings().group(GoblinGroup.GOBLIN_GROUP));//经验球掉落的值的范围

    //发酵器
    public static final Block FERMENTATION_VESSELS_BLOCK = registerBlock("fermentation_vessels_block", new fermentationVesselsBlock(FabricBlockSettings.of(Material.STONE)));
    public static BlockEntityType<fermentationVesselsBlockEntity> FERMENTATION_VESSELS_BLOCK_ENTITY =
            registerBlockEntity("fermentation_vessels_block_entity", FabricBlockEntityTypeBuilder.create(fermentationVesselsBlockEntity::new, FERMENTATION_VESSELS_BLOCK).build(null));
    public static final ScreenHandlerType<fermentationVesselsBlockScreenHandler> FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER;
    static {
        FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier("goblin-mod","fermentation_vessels_block"), fermentationVesselsBlockScreenHandler::new);
    }
    //哥布林
    public static final Item GOBLIN_MEAT = registerItem("goblin_meat", new Item(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_SINEWS = registerItem("goblin_sinews", new Item(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_LEATHER = registerItem("goblin_leather", new Item(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_LEATHER_BAG = registerItem("goblin_leather_bag", new GoblinLeatherBagItem(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_INGOT = registerItem("goblin_ingot", new Item(new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    //攻速默认4f，如果想设置3.2f的攻速，写-0.8f
    public static final Item GOBLIN_SWORD = registerItem("goblin_sword", new GoblinSword(new GoblinToolMaterial(), 8, -0.8f, new FabricItemSettings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_AXE = registerItem("goblin_axe", new GoblinAxe(new GoblinToolMaterial(), 10, -1.6f, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_HOE = registerItem("goblin_hoe", new GoblinHoe(new GoblinToolMaterial(), 1, -3.0f, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_PICKAXE = registerItem("goblin_pickaxe", new GoblinPickaxe(new GoblinToolMaterial(), 5, -2.8f, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final Item GOBLIN_SHOVEL = registerItem("goblin_shovel", new GoblinShovel(new GoblinToolMaterial(), 3, -3.0f, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    //盔甲
    public static final ArmorMaterial GOBLIN_ARMOR_MATERIAL = new GoblinArmorMaterial();//多态
    public static final ArmorItem GOBLIN_HELMET = registerArmorItem("goblin_helmet",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_CHESTPLATE = registerArmorItem("goblin_chestplate",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_LEGGINGS = registerArmorItem("goblin_leggings",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
    public static final ArmorItem GOBLIN_BOOTS = registerArmorItem("goblin_boots",new GoblinArmorItem(GOBLIN_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));

    public static final Item GOBLIN_SPAWN_EGG = registerItem("goblin_spawn_egg", new SpawnEggItem(ModEntities.GOBLIN_ENTITY, 0x70B319,0x302B11, new Item.Settings()));


    //注册物品
    private static Item registerItem(String name, Item item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }
    //注册盔甲物品
    private static ArmorItem registerArmorItem(String name, ArmorItem item){
        return Registry.register(Registry.ITEM, new Identifier("goblin-mod",name), item);
    }
    //注册方块和对应物品
    private static Block registerBlock(String name, Block block){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, new Item.Settings().group(GoblinGroup.GOBLIN_GROUP)));
        return block;
    }
    //注册方块和对应的物品, 可以对物品进行设置
    private static Block registerBlock(String name, Block block, Item.Settings itemSettings){
        Registry.register(Registry.BLOCK, new Identifier("goblin-mod", name), block);
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", name), new BlockItem(block, itemSettings));
        return block;
    }
    //注册方块实体
    private static <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(String name, BlockEntityType<T> blockEntityType){
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier("goblin-mod", name), blockEntityType);
    }

    public static void setGoblinGroupThings(){
        GoblinGroup.setGoblinGroup();
    }
}
