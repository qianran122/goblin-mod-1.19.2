package top.qianran.recipes;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;
import top.qianran.items.CopyItem;
import top.qianran.util.ModItems;
import top.qianran.util.ModRecipes;

public class CopyRecipe extends SpecialCraftingRecipe {
    public CopyRecipe(Identifier id) {
        super(id);
    }

    private int copyItemSlot;
    private int itemSlot;
    private int copies;

    /*@Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        int copyItemCount = 0;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() == ModItems.COPY_ITEM){
                    if (stack.hasNbt()) {
                        assert stack.getNbt() != null;
                        if(stack.getNbt().get("amount") != null){
                            count++;
                            copyItemCount++;
                        }
                    }
                } else {
                    count++;
                }
            }
        }
        return count == 2 && copyItemCount == 1;
    }*/

    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() == ModItems.COPY_ITEM && stack.getCount() == 1){
                    if(stack.hasNbt()){
                        assert stack.getNbt() != null;
                        if(stack.getNbt().get("amount") != null){
                            count++;
                            copyItemCount++;
                        }
                    }
                } else {
                    count++;
                }
            }
        }
        return count == 2 && copyItemCount == 1;
    }

   /* @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        int copyItemCount = 0;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() == Items.DIAMOND || stack.getItem() == Items.DIAMOND_BLOCK){
                    return ItemStack.EMPTY;
                }
                if(stack.getItem() == ModItems.COPY_ITEM){
                    if (stack.hasNbt()) {
                        assert stack.getNbt() != null;
                        if(stack.getNbt().get("amount") != null){
                            count++;
                            copyItemCount++;
                            copyItemSlot = i;
                        }
                    }
                } else {
                    count++;
                    itemSlot = i;
                }
            }
        }
        if(count == 2 && copyItemCount == 1){
            ItemStack stack = inventory.getStack(copyItemSlot);
            assert stack.getNbt() != null;
            int amount = stack.getNbt().getInt("amount");//"amount": int
            ItemStack stack1 = inventory.getStack(itemSlot);
            Item item = inventory.getStack(itemSlot).getItem();
            if(stack1.hasNbt()){
                NbtCompound nbt = stack1.getNbt();
                ItemStack stack2 = new ItemStack(item, amount + 1);
                stack2.setNbt(nbt);
                return stack2;
            }
            return new ItemStack(item, amount+1);
        }
        return ItemStack.EMPTY;
    }*/

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        int copyItemCount = 0;
        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                if(stack.getItem() == Items.DIAMOND || stack.getItem() == Items.DIAMOND_BLOCK){
                    return ItemStack.EMPTY;
                }
                if(stack.getItem() == ModItems.COPY_ITEM && stack.getCount() == 1){
                    if(stack.hasNbt()){
                        assert stack.getNbt() != null;
                        if(stack.getNbt().get("amount") != null) {
                            count++;
                            copyItemCount++;
                            copyItemSlot = i;
                            copies = stack.getNbt().getInt("amount");
                        }
                    }
                } else {
                    count++;
                    itemSlot = i;
                }
            }
        }
        if(count == 2 && copyItemCount ==1){
            ItemStack stack = inventory.getStack(itemSlot);
            Item item = stack.getItem();
            ItemStack newStack = new ItemStack(item, 2);
            if(stack.hasNbt()){
                NbtCompound nbt = stack.getNbt();
                newStack.setNbt(nbt);
            }
            return newStack;
        }
        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.COPY_RECIPE;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        if(copies > 1){
            ItemStack stack = new ItemStack(ModItems.COPY_ITEM, 2);
            stack.setNbt(CopyItem.newCopyItemNbt(copies - 1));
            inventory.setStack(copyItemSlot, stack);
        }
        return super.getRemainder(inventory);
    }
}
