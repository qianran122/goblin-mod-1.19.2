package top.qianran.util;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.qianran.entity.ModEntity;
import top.qianran.entity.custom.*;

public class Registries {

    //哥布林物品组
    public static final ItemGroup GOBLIN_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("goblin-mod","goblin_item"))
            .icon(() -> new ItemStack(ModItems.GOBLIN_MEAT))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(ModItems.JB_ITEM));
                stacks.add(new ItemStack(ModItems.GOBLIN_MEAT));
                stacks.add(new ItemStack(ModItems.GOBLIN_INGOT));
                //神秘木系列
                stacks.add(new ItemStack(ModBlocks.MYSTERIOUS_LOG));
                stacks.add(new ItemStack(ModBlocks.MYSTERIOUS_WOOD));
                stacks.add(new ItemStack(ModBlocks.MYSTERIOUS_LOG_STRIPPED));
                stacks.add(new ItemStack(ModBlocks.MYSTERIOUS_WOOD_STRIPPED));
                stacks.add(new ItemStack(ModBlocks.MYSTERIOUS_PLANKS));
                //武器与工具
                stacks.add(new ItemStack(ModItems.GOBLIN_SWORD));
                stacks.add(new ItemStack(ModItems.GOBLIN_AXE));
                stacks.add(new ItemStack(ModItems.GOBLIN_HOE));
                stacks.add(new ItemStack(ModItems.GOBLIN_PICKAXE));
                stacks.add(new ItemStack(ModItems.GOBLIN_SHOVEL));
                //盔甲
                stacks.add(new ItemStack(ModItems.GOBLIN_HELMET));
                stacks.add(new ItemStack(ModItems.GOBLIN_CHESTPLATE));
                stacks.add(new ItemStack(ModItems.GOBLIN_LEGGINGS));
                stacks.add(new ItemStack(ModItems.GOBLIN_BOOTS));

                //生成蛋
                stacks.add(new ItemStack(ModItems.GOBLIN_SPAWN_EGG));
            })
            .build();
    public static void init(){
        registerAttributes();
        flammableBlockRegistry();
        strippableRegistry();
    }

    //为实体注册属性
    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntity.SHIT_ENTITY, ShitEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.SHITA_ENTITY, ShitAEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.GOBLIN_ENTITY, GoblinEntity.setAttributes());
    }

    //将方块注册为可剥皮
    private static void strippableRegistry() {
        StrippableBlockRegistry.register(ModBlocks.MYSTERIOUS_LOG, ModBlocks.MYSTERIOUS_LOG_STRIPPED);
        StrippableBlockRegistry.register(ModBlocks.MYSTERIOUS_WOOD, ModBlocks.MYSTERIOUS_WOOD_STRIPPED);

    }

    //将方块注册为可燃烧
    private static void flammableBlockRegistry() {
        FlammableBlockRegistry registry = FlammableBlockRegistry.getDefaultInstance();
        registry.add(ModBlocks.MYSTERIOUS_LOG, 5, 5);
        registry.add(ModBlocks.MYSTERIOUS_WOOD, 5, 5);
        registry.add(ModBlocks.MYSTERIOUS_LOG_STRIPPED, 5, 5);
        registry.add(ModBlocks.MYSTERIOUS_WOOD_STRIPPED, 5, 5);
        registry.add(ModBlocks.MYSTERIOUS_PLANKS, 5, 20);

    }
}
