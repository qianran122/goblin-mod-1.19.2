/*
package top.qianran.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import top.qianran.util.ModItems;

public class CopyItemRecipe extends SpecialCraftingRecipe {
    public CopyItemRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasPaper = false;
        boolean hasDiamond = false;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() == Items.PAPER){
                    hasPaper = true;
                } else if(stack.getItem() == Items.DIAMOND){
                    hasDiamond = true;
                } else {
                    return false;
                }
            }
        }
        return hasDiamond && hasPaper;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        boolean hasPaper = false;
        boolean hasDiamond = false;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() != Items.PAPER && stack.getItem() != Items.DIAMOND){
                    return ItemStack.EMPTY;
                }
                if(stack.getItem() == Items.PAPER && hasPaper){
                    return ItemStack.EMPTY;
                }
                if(stack.getItem() == Items.DIAMOND && hasDiamond){
                    return ItemStack.EMPTY;
                }
                if(stack.getItem() == Items.PAPER){
                    hasPaper = true;
                }
                if(stack.getItem() == Items.DIAMOND){
                    hasDiamond = true;
                    count++;
                }
            }
        }
        if(hasDiamond && hasPaper){
            ItemStack stack = new ItemStack(ModItems.COPY_ITEM);
        }
    }

    @Override
    public boolean fits(int width, int height) {
        return false;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return null;
    }
}
*/
