package top.qianran.util;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialRecipeSerializer;
import top.qianran.recipes.BowlOfWaterRecipe;

public class ModRecipes {
    public static final RecipeSerializer<BowlOfWaterRecipe> BOWL_OF_WATER_RECIPE = RecipeSerializer.register("crafting_bowl_of_water", new SpecialRecipeSerializer<>(BowlOfWaterRecipe::new));
    public static void init(){
    }
}
