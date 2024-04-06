package top.qianran.dataGenerator;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.datafixer.fix.RecipeRenameFix;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.util.registry.Registry;
import org.spongepowered.asm.mixin.extensibility.IRemapper;
import top.qianran.util.ModBlocks;
import top.qianran.util.ModItems;

import java.util.function.Consumer;

import static net.minecraft.world.gen.feature.Feature.SIMPLE_BLOCK;

public class MyRecipeGenerator extends FabricRecipeProvider {
    public MyRecipeGenerator(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }



    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
            //一个红宝石块合成9个红宝石
            ShapelessRecipeJsonBuilder.create(ModItems.RED_DIAMOND, 9)
                    .input(ModBlocks.RED_DIAMOND_BLOCK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.RED_DIAMOND),
                            FabricRecipeProvider.conditionsFromItem(ModItems.RED_DIAMOND))
                    .criterion(FabricRecipeProvider.hasItem(ModBlocks.RED_DIAMOND_BLOCK),
                            FabricRecipeProvider.conditionsFromItem(ModBlocks.RED_DIAMOND_BLOCK))
                    .offerTo(exporter);

            //哥布林镐子合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_PICKAXE, 1)
                    .pattern("ggg")
                    .pattern(" s ")
                    .pattern(" s ")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK))
                    .offerTo(exporter);
            //哥布林铲子合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_SHOVEL, 1)
                    .pattern(" g ")
                    .pattern(" s ")
                    .pattern(" s ")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK))
                    .offerTo(exporter);

            //哥布林斧头合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_AXE, 1)
                    .pattern("gg ")
                    .pattern("gs ")
                    .pattern(" s ")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK))
                    .offerTo(exporter);

            //哥布林锄头合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_HOE, 1)
                    .pattern("gg ")
                    .pattern(" s ")
                    .pattern(" s ")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK))
                    .offerTo(exporter);

            //哥布林剑合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_SWORD, 1)
                    .pattern(" g ")
                    .pattern(" g ")
                    .pattern(" s ")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .input('s', Items.STICK)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .criterion(FabricRecipeProvider.hasItem(Items.STICK),
                            FabricRecipeProvider.conditionsFromItem(Items.STICK))
                    .offerTo(exporter);

            //哥布林头盔合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_HELMET, 1)
                    .pattern("ggg")
                    .pattern("g g")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .offerTo(exporter);

            //哥布林胸甲合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_CHESTPLATE, 1)
                    .pattern("g g")
                    .pattern("ggg")
                    .pattern("ggg")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .offerTo(exporter);

            //哥布林护腿合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_LEGGINGS, 1)
                    .pattern("ggg")
                    .pattern("g g")
                    .pattern("g g")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .offerTo(exporter);

            //哥布林靴子合成
            ShapedRecipeJsonBuilder.create(ModItems.GOBLIN_BOOTS, 1)
                    .pattern("g g")
                    .pattern("g g")
                    .input('g', ModItems.GOBLIN_MEAT)
                    .criterion(FabricRecipeProvider.hasItem(ModItems.GOBLIN_MEAT),
                            FabricRecipeProvider.conditionsFromItem(ModItems.GOBLIN_MEAT))
                    .offerTo(exporter);

    }
}
