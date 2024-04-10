package top.qianran.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import top.qianran.items.GoblinLeatherBagItem;
import top.qianran.items.group.GoblinGroupThings;
import top.qianran.util.ModRecipes;

public class GoblinLeatherBagRecipe extends SpecialCraftingRecipe {


    public GoblinLeatherBagRecipe(Identifier id) {
        super(id);
    }


    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int goblinLeatherCount = 0;
        boolean hasGoblinSinews = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()) {
                if (stack.getItem() == GoblinGroupThings.GOBLIN_SINEWS) {
                    hasGoblinSinews = true;
                }
                if(stack.getItem() == GoblinGroupThings.GOBLIN_LEATHER){
                    goblinLeatherCount++;
                }
            }
        }
        if(hasGoblinSinews && goblinLeatherCount == 3){
            return true;
        }
        return false;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int goblinLeatherCount = 0;
        boolean hasGoblinSinews = false;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()) {
                if (stack.getItem() == GoblinGroupThings.GOBLIN_SINEWS) {
                    hasGoblinSinews = true;
                }
                if(stack.getItem() == GoblinGroupThings.GOBLIN_LEATHER){
                    goblinLeatherCount++;
                }
            }
        }
        if(hasGoblinSinews && goblinLeatherCount == 3){
            ItemStack stack = new ItemStack(GoblinGroupThings.GOBLIN_LEATHER_BAG,1);
            stack.setNbt(GoblinLeatherBagItem.unUsedNbt());
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        return super.getRemainder(inventory);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.GOBLIN_LEATHER_BAG_RECIPE;
    }
}
