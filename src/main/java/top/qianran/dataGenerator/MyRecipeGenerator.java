package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import top.qianran.items.group.GoblinGroupThings;

import java.util.function.Consumer;


public class MyRecipeGenerator extends FabricRecipeProvider {
    public MyRecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }
    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        BLOCKS(exporter);//方块
        TOOLS(exporter);//工具
        MYSTERIOUS_TREE(exporter);//神秘树合成
        GOBLIN_TOOLS_AND_ARMORS(exporter);//哥布林工具和盔甲
        RED_DIAMOND(exporter);//红宝石块合成红宝石


    }
    private static void BLOCKS(Consumer<RecipeJsonProvider> exporter){
        //发酵器
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.FERMENTATION_VESSELS_BLOCK, 1)
                .pattern("glg")
                .pattern("gsg")
                .pattern("ggg")
                .input('l', GoblinGroupThings.MYSTERIOUS_LEAVES)
                .input('g', Items.IRON_INGOT)
                .input('s', Items.GLASS)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_LEAVES),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_LEAVES))
                .criterion(FabricRecipeProvider.hasItem(Items.IRON_INGOT),
                        FabricRecipeProvider.conditionsFromItem(Items.IRON_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.GLASS),
                        FabricRecipeProvider.conditionsFromItem(Items.GLASS))
                .offerTo(exporter);
    }


    private static void TOOLS(Consumer<RecipeJsonProvider> exporter){


    }


    private static void MYSTERIOUS_TREE(Consumer<RecipeJsonProvider> exporter) {
        //神秘树合成神秘木板
        ShapelessRecipeJsonBuilder.create(GoblinGroupThings.MYSTERIOUS_PLANKS, 4)
                .input(GoblinGroupThings.MYSTERIOUS_LOG)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_LOG),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_LOG))
                .offerTo(exporter);
        //神秘木板合成工作台
        ShapedRecipeJsonBuilder.create(Blocks.CRAFTING_TABLE, 1)
                .pattern("pp")
                .pattern("pp")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .offerTo(exporter);
        //神秘木板合成箱子
        ShapedRecipeJsonBuilder.create(Blocks.CHEST, 1)
                .pattern("ppp")
                .pattern("p p")
                .pattern("ppp")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .offerTo(exporter);
        //神秘木板合成木棍
        ShapedRecipeJsonBuilder.create(Items.STICK, 4)
                .pattern("p")
                .pattern("p")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .offerTo(exporter);
        //神秘木板合成木斧
        ShapedRecipeJsonBuilder.create(Items.WOODEN_AXE, 1)
                .pattern("pp ")
                .pattern("ps ")
                .pattern(" s ")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        //神秘木板合成木剑
        ShapedRecipeJsonBuilder.create(Items.WOODEN_SWORD, 1)
                .pattern(" p ")
                .pattern(" p ")
                .pattern(" s ")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        //神秘木板合成木镐
        ShapedRecipeJsonBuilder.create(Items.WOODEN_PICKAXE, 1)
                .pattern("ppp")
                .pattern(" s ")
                .pattern(" s ")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        //神秘木板合成木锄
        ShapedRecipeJsonBuilder.create(Items.WOODEN_SHOVEL, 1)
                .pattern("pp ")
                .pattern(" s ")
                .pattern(" s ")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
        //神秘木板合成木锹
        ShapedRecipeJsonBuilder.create(Items.WOODEN_HOE, 1)
                .pattern(" p ")
                .pattern(" s ")
                .pattern(" s ")
                .input('p', GoblinGroupThings.MYSTERIOUS_PLANKS)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.MYSTERIOUS_PLANKS),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.MYSTERIOUS_PLANKS))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }

    //哥布林工具和盔甲
    private static void GOBLIN_TOOLS_AND_ARMORS(Consumer<RecipeJsonProvider> exporter){

        recipesGOBLIN_PICKAXE(exporter);//哥布林镐子合成
        recipesGOBLIN_SHOVEL(exporter);//哥布林铲子合成
        recipesGOBLIN_AXE(exporter);//哥布林斧头合成
        recipesGOBLIN_HOE(exporter);//哥布林锄头合成
        recipesGOBLIN_SWORD(exporter);//哥布林剑合成
        recipesGOBLIN_HELMET(exporter);//哥布林头盔合成
        recipesGOBLIN_CHESTPATE(exporter);//哥布林胸甲合成
        recipesGOBLIN_LEGGINGS(exporter);//哥布林护腿合成
        recipesGOBLIN_BOOTS(exporter);//哥布林靴子合成
    }
    private static void recipesGOBLIN_PICKAXE(Consumer<RecipeJsonProvider> exporter){
        //哥布林镐子合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_PICKAXE, 1)
                .pattern("ggg")
                .pattern(" s ")
                .pattern(" s ")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_SHOVEL(Consumer<RecipeJsonProvider> exporter) {
        //哥布林铲子合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_SHOVEL, 1)
                .pattern(" g ")
                .pattern(" s ")
                .pattern(" s ")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_AXE(Consumer<RecipeJsonProvider> exporter) {
        //哥布林斧头合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_AXE, 1)
                .pattern("gg ")
                .pattern("gs ")
                .pattern(" s ")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_HOE(Consumer<RecipeJsonProvider> exporter) {
        //哥布林锄头合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_HOE, 1)
                .pattern("gg ")
                .pattern(" s ")
                .pattern(" s ")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_SWORD(Consumer<RecipeJsonProvider> exporter) {
        //哥布林剑合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_SWORD, 1)
                .pattern(" g ")
                .pattern(" g ")
                .pattern(" s ")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .input('s', Items.STICK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                        FabricRecipeProvider.conditionsFromItem(Items.STICK))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_HELMET(Consumer<RecipeJsonProvider> exporter) {
        //哥布林头盔合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_HELMET, 1)
                .pattern("ggg")
                .pattern("g g")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_CHESTPATE(Consumer<RecipeJsonProvider> exporter) {
        //哥布林胸甲合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_CHESTPLATE, 1)
                .pattern("g g")
                .pattern("ggg")
                .pattern("ggg")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_LEGGINGS(Consumer<RecipeJsonProvider> exporter) {
        //哥布林护腿合成
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_LEGGINGS, 1)
                .pattern("ggg")
                .pattern("g g")
                .pattern("g g")
                .input('g', GoblinGroupThings.GOBLIN_INGOT)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                .offerTo(exporter);
    }
    private static void recipesGOBLIN_BOOTS(Consumer<RecipeJsonProvider> exporter){
            //哥布林靴子合成
            ShapedRecipeJsonBuilder.create(GoblinGroupThings.GOBLIN_BOOTS, 1)
                    .pattern("g g")
                    .pattern("g g")
                    .input('g', GoblinGroupThings.GOBLIN_INGOT)
                    .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.GOBLIN_INGOT),
                            FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.GOBLIN_INGOT))
                    .offerTo(exporter);
    }

    private static void RED_DIAMOND(Consumer<RecipeJsonProvider> exporter) {
        //一个红宝石块合成9个红宝石
        ShapelessRecipeJsonBuilder.create(GoblinGroupThings.RED_DIAMOND, 9)
                .input(GoblinGroupThings.RED_DIAMOND_BLOCK)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.RED_DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.RED_DIAMOND))
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.RED_DIAMOND_BLOCK),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.RED_DIAMOND_BLOCK))
                .offerTo(exporter);
        //9个红宝石合成一个红宝石块
        ShapedRecipeJsonBuilder.create(GoblinGroupThings.RED_DIAMOND_BLOCK, 1)
                .pattern("rrr")
                .pattern("rrr")
                .pattern("rrr")
                .input('r', GoblinGroupThings.RED_DIAMOND)
                .criterion(FabricRecipeProvider.hasItem(GoblinGroupThings.RED_DIAMOND),
                        FabricRecipeProvider.conditionsFromItem(GoblinGroupThings.RED_DIAMOND))
                .offerTo(exporter);
    }
}


