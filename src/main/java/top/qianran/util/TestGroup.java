package top.qianran.util;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import top.qianran.GoblinMod;

public class TestGroup {
    //测试物品组
    public static final ItemGroup TEST_GROUP = FabricItemGroupBuilder.create(
                    new Identifier("goblin-mod","test_item"))
            .icon(() -> new ItemStack(ModItems.TEST1))
            .appendItems(stacks -> {
                stacks.add(new ItemStack(GoblinMod.EXAMPLE_ITEM));
                stacks.add(new ItemStack(GoblinMod.BOX_BLOCK));
                stacks.add(new ItemStack(GoblinMod.DEMO_BLOCK));
                stacks.add(new ItemStack(GoblinMod.CUBE_SPAWN_EGG));
                stacks.add(new ItemStack(ModItems.SHIT_SPAWN_EGG));
                stacks.add(new ItemStack(ModItems.SHITA_SPAWN_EGG));

                stacks.add(new ItemStack(ModItems.TEST1));
                stacks.add(new ItemStack(ModItems.TEST2));
                stacks.add(new ItemStack(ModItems.TEST3));
                stacks.add(new ItemStack(ModItems.JB_ITEM));
                stacks.add(new ItemStack(ModItems.RED_DIAMOND));
                stacks.add(new ItemStack(ModItems.BOWL_OF_WATER));
                stacks.add(new ItemStack(GoblinMod.POLISHED_ANDESITE_VERTICAL_SLAB));
                //方块
                stacks.add(new ItemStack(ModBlocks.RED_DIAMOND_BLOCK));
                stacks.add(new ItemStack(ModBlocks.TEST_BOX_BLOCK));
                stacks.add(new ItemStack(ModBlocks.CHANGE_BLOCK));
                stacks.add(new ItemStack(ModBlocks.NUMBER_BLOCK));
                //带方块实体的方块
                stacks.add(new ItemStack(ModBlocks.BREAK_BLOCK));
                stacks.add(new ItemStack(ModBlocks.CONTAINER_BLOCK));
                stacks.add(new ItemStack(ModBlocks.NEW_BOX_BLOCK));
                stacks.add(new ItemStack(ModBlocks.UI_BLOCK));
            })
            .build();

    //test
    public static void init(){
    }
}
