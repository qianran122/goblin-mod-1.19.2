package top.qianran.test;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.*;
import top.qianran.entity.custom.CubeEntity;

public class Test {

    public static final VerticalSlabBlock POLISHED_ANDESITE_VERTICAL_SLAB = Registry.register(
            Registry.BLOCK,
            new Identifier("goblin-mod", "polished_andesite_vertical_slab"),
            new VerticalSlabBlock(FabricBlockSettings.copyOf(Blocks.POLISHED_ANDESITE)));

    //注册方块与方块实体
    public static final Block DEMO_BLOCK = Registry.register(
            Registry.BLOCK,
            new Identifier("goblin-mod","demo_block"),
            new Block(FabricBlockSettings.copyOf(Blocks.STONE))
    );
    public static final BlockEntityType<DemoBlockEntity> DEMO_BLOCK_ENTITY = Registry.register(
            Registry.BLOCK_ENTITY_TYPE,
            new Identifier("goblin-mod", "demo_block_entity"),
            BlockEntityType.Builder.create(DemoBlockEntity::new, DEMO_BLOCK).build(null));

    //注册方块，物品和方块实体
    //创建容器方块
    public static final Block BOX_BLOCK;
    public static final BlockItem BOX_BLOCK_ITEM;
    public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;
    public static final String MOD_ID = "goblin-mod";
    // 我们的大型箱子中不同部分的公共id
    public static final Identifier BOX = new Identifier(MOD_ID, "box_block");
    static {
        BOX_BLOCK = Registry.register(Registry.BLOCK, BOX, new BoxBlock(FabricBlockSettings.copyOf(Blocks.CHEST)));
        BOX_BLOCK_ITEM = Registry.register(Registry.ITEM, BOX, new BlockItem(BOX_BLOCK, new Item.Settings()));

        //The parameter of build at the very end is always null, do not worry about it
        // 1.17 之前
        //BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, BlockEntityType.Builder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
        // 在 1.17 使用 FabricBlockEntityTypeBuilder 而不是 BlockEntityType.Builder
        BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX, FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));
    }
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;
    static
    {
        // 我们在这里使用 registerSimple 因为实体不是 ExtendedScreenHandlerFactory
        // 而是 NamedScreenHandlerFactory.
        // 后面的教程中，你将会看到 ExtendedScreenHandlerFactory 能做什么！
        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerExtended(BOX, BoxScreenHandler::new);
    }


    /*
         * 使用“goblin-mod:cube”作为ID注册我们的实体
         *
    //	 * 这个实体注册在了 SpawnGroup#CREATURE 类别下，大多数的动物和友好或中立的生物都注册在这个类别下。
         * 它有一个 0.75 × 0.75（或12个像素宽，即一个方块的3/4）大小的碰撞体积。
         */
    public static final EntityType<CubeEntity> CUBE = Registry.register(
            Registry.ENTITY_TYPE,
            new Identifier("goblin-mod", "cube"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CubeEntity::new).dimensions(EntityDimensions.fixed(0.75f, 0.75f)).build()
    );

    //创建一个刷怪蛋物品的实例
    public static final Item CUBE_SPAWN_EGG = new SpawnEggItem(CUBE, 0xc4c4c4, 0xadadad, new FabricItemSettings());

    //战利品表
    //private static final Identifier GOBLIN_ENTITY_LOOT_TABLE_ID = ModEntities.GOBLIN_ENTITY.getLootTableId();

    public static void test(){

        Registry.register(Registry.ITEM, new Identifier("goblin-mod", "polished_andesite_vertical_slab"),
                new BlockItem(POLISHED_ANDESITE_VERTICAL_SLAB, new Item.Settings()));

        //注册demo_block物品
        Registry.register(Registry.ITEM, new Identifier("goblin-mod","demo_block"),
                new BlockItem(DEMO_BLOCK, new Item.Settings()));

        /*
         * 注册我们方块实体的默认属性。
         * 属性是一个生物当前状态的数值，其中有攻击伤害和生命值等。
         * 如果实体没有及时注册适当的属性，则游戏将崩溃。
         *
         * 在1.15中，它是通过重写实体类内部的方法来完成的。
         * 大部分的原版实体都有一个静态方法(例如,ZombieEntity#createZombieAttributes)用于初始化它们的属性。
         */
        FabricDefaultAttributeRegistry.register(CUBE, CubeEntity.createMobAttributes());

        //注册刷怪蛋物品
        Registry.register(Registry.ITEM, new Identifier("goblin-mod", "cube_spawn_egg"), CUBE_SPAWN_EGG);







    }




}

