package top.qianran.util;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.qianran.entity.ModEntity;
import top.qianran.entity.custom.*;

public class Registries {


    public static void init(){
        registerAttributes();
    }


    //哥布林物品组
    public static final ItemGroup GOBLIN_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("goblin-mod","goblin_item"))
            .icon(() -> new ItemStack(ModItems.GOBLIN_MEAT))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(ModItems.JB_ITEM));
                stacks.add(new ItemStack(ModItems.GOBLIN_MEAT));
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

    //为实体注册属性
    private static void registerAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntity.SHIT_ENTITY, ShitEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.SHITA_ENTITY, ShitAEntity.setAttributes());
        FabricDefaultAttributeRegistry.register(ModEntity.GOBLIN_ENTITY, GoblinEntity.setAttributes());
    }
}
