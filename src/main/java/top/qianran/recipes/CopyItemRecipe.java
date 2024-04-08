package top.qianran.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import top.qianran.GoblinMod;
import top.qianran.util.ModItems;
import top.qianran.util.ModRecipes;

public class CopyItemRecipe extends SpecialCraftingRecipe {
    public CopyItemRecipe(Identifier id) {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        boolean hasDiamond = false;
        boolean hasPaper = false;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() != Items.DIAMOND && stack.getItem() != Items.PAPER) return false;
                if(stack.getItem() == Items.PAPER && hasPaper) return false;
                if(stack.getItem() == Items.DIAMOND) hasDiamond = true;
                if(stack.getItem() == Items.PAPER) hasPaper = true;
            }
        }
        return hasDiamond && hasPaper;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        boolean hasDiamond = false;
        boolean hasPaper = false;
        int count = 0;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() != Items.DIAMOND && stack.getItem() != Items.PAPER) return ItemStack.EMPTY;
                if(stack.getItem() == Items.PAPER && hasPaper) return ItemStack.EMPTY;
                if(stack.getItem() == Items.DIAMOND) {
                    hasDiamond = true;
                    count++;
                }
                if(stack.getItem() == Items.PAPER) {
                    hasPaper = true;
                }
            }
        }
        if(hasPaper && hasDiamond){
            ItemStack stack = new ItemStack(ModItems.COPY_ITEM);
            NbtCompound nbt = new NbtCompound();
            nbt.putInt("amount", count);
            NbtString string = NbtString.of(NbtString.escape("Number of copies: " + count));
            NbtList list = new NbtList();
            list.add(string);
            NbtCompound nbt1 = new NbtCompound();
            nbt1.put("Lore", list);
            nbt.put("display", nbt1);
            stack.setNbt(nbt);
            return stack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width* height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COPY_ITEM_RECIPE;
    }
}
