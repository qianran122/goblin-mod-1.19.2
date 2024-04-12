package top.qianran.test.exampleRecipesBlock;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ExampleRecipeSerializer implements RecipeSerializer<ExampleRecipe> {
    public static final ExampleRecipeSerializer INSTANCE = new ExampleRecipeSerializer();
    //这个ID是用来标识这个序列化器的，用于在数据包中传输
    public static final Identifier ID = new Identifier("goblin-mod", "example_recipe");
    @Override
    public ExampleRecipe read(Identifier id, JsonObject json) {
        ExampleRecipeJsonFormat recipeJson = new Gson().fromJson(json, ExampleRecipeJsonFormat.class);
        //
        if(recipeJson.input1 == null || recipeJson.input2 == null || recipeJson.outputItem == null){
            throw new JsonSyntaxException("A required attribute is missing!");
        }
        //
        if(recipeJson.outputCount == 0) recipeJson.outputCount = 1;

        Ingredient input1 = Ingredient.fromJson(recipeJson.input1);
        Ingredient input2 = Ingredient.fromJson(recipeJson.input2);

        Item outputItem = Registry.ITEM.getOrEmpty(new Identifier(recipeJson.outputItem))
                //如果没有这个物品，抛出异常
                .orElseThrow(() -> new JsonSyntaxException("No such item " + recipeJson.outputItem));
        ItemStack output = new ItemStack(outputItem, recipeJson.outputCount);

        return new ExampleRecipe(input1, input2, output, id);
    }

    @Override
    public ExampleRecipe read(Identifier id, PacketByteBuf buf) {
        Ingredient input1 = Ingredient.fromPacket(buf);
        Ingredient input2 = Ingredient.fromPacket(buf);
        ItemStack output = buf.readItemStack();
        return new ExampleRecipe(input1, input2, output, id);
    }

    @Override
    public void write(PacketByteBuf buf, ExampleRecipe recipe) {
        recipe.getInput1().write(buf);
        recipe.getInput2().write(buf);
        buf.writeItemStack(recipe.getOutput());
    }
}
