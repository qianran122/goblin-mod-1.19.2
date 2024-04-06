package top.qianran.blocks.exampleRecipesBlock;

import com.google.gson.JsonObject;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class ExampleRecipe implements Recipe<Inventory> {


    private final Ingredient input1;
    private final Ingredient input2;
    private final ItemStack output;
    private final Identifier id;
    public ExampleRecipe(Ingredient input1, Ingredient input2, ItemStack output, Identifier id) {
        this.input1 = input1;
        this.input2 = input2;
        this.output = output;
        this.id = id;
    }

    public Ingredient getInput1() {
        return input1;
    }

    public Ingredient getInput2() {
        return input2;
    }
    @Override
    public boolean matches(Inventory inventory, World world) {
        if(inventory.size() < 2) {
            return false;
        }
        //
        return ( ( this.input1.test(inventory.getStack(0)) && this.input2.test(inventory.getStack(1)) )
                || ( this.input1.test(inventory.getStack(1)) && this.input2.test(inventory.getStack(0)) ) );
    }

    @Override
    public ItemStack craft(Inventory inventory) {
        return this.output.copy();//避免浅拷贝
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getOutput() {
        return this.output;
    }

    @Override
    public Identifier getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ExampleRecipeSerializer.INSTANCE;
    }

    //现在我们需要给予这个配方一个类型，你所要做的是创建一个继承 RecipeType<TestRecipe> 的实例。
    public static class Type implements RecipeType<ExampleRecipe> {
        private Type() {
        }
        public static final Type INSTANCE = new Type();
        public static final String ID = "example_recipe";
    }
    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }
}
class ExampleRecipeJsonFormat {
    JsonObject input1;
    JsonObject input2;
    String outputItem;
    int outputCount;
}
