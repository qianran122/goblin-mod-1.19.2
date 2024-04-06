package top.qianran.util;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import top.qianran.recipes.BowlOfWaterRecipe;
import top.qianran.blocks.exampleRecipesBlock.ExampleRecipe;
import top.qianran.blocks.exampleRecipesBlock.ExampleRecipeSerializer;

public class ModRecipes {
    public static final RecipeSerializer<BowlOfWaterRecipe> BOWL_OF_WATER_RECIPE = RecipeSerializer.register("crafting_bowl_of_water", new SpecialRecipeSerializer<>(BowlOfWaterRecipe::new));


    public static void init(){
        //注册合成表
        Registry.register(Registry.RECIPE_SERIALIZER, ExampleRecipeSerializer.ID, ExampleRecipeSerializer.INSTANCE);
        Registry.register(Registry.RECIPE_TYPE, new Identifier("goblin-mod", ExampleRecipe.Type.ID), ExampleRecipe.Type.INSTANCE);
    }
}
