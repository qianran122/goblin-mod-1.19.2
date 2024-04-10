package top.qianran.recipes;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.qianran.items.GoblinLeatherBagItem;
import top.qianran.items.group.GoblinGroupThings;
import top.qianran.util.ModRecipes;

import java.util.ArrayList;

public class UseGoblinLeatherBag extends SpecialCraftingRecipe {
    public UseGoblinLeatherBag(Identifier id) {
        super(id);
    }

    public static final Logger LOGGER = LoggerFactory.getLogger(UseGoblinLeatherBag.class.getName());
    @Override
    public boolean matches(CraftingInventory inventory, World world) {
        int count = 0;
        int goblinLeatherBagCount = 0;
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                count++;
                if(stack.getItem() == GoblinGroupThings.GOBLIN_LEATHER_BAG){
                    if(stack.hasNbt()){
                        assert stack.getNbt() != null;
                        String s = stack.getNbt().getString("use");
                        //LOGGER.info("matches() use: " + s);
                        if(s.equals("used")){
                            return false;
                        }
                        goblinLeatherBagCount++;
                    }
                }
            }
        }
        return count >= 2 && goblinLeatherBagCount == 1;
    }

    @Override
    public ItemStack craft(CraftingInventory inventory) {
        int count = 0;
        int goblinLeatherBagCount = 0;
        ArrayList<Integer> slots = new ArrayList<>();
        for(int i = 0; i < inventory.size(); ++i){
            ItemStack stack = inventory.getStack(i);
            if(!stack.isEmpty()){
                count++;
                slots.add(i);
                if(stack.getItem() == GoblinGroupThings.GOBLIN_LEATHER_BAG){
                    if(stack.getCount() != 1){
                        return ItemStack.EMPTY;
                    }
                    if(stack.hasNbt()){
                        assert stack.getNbt() != null;
                        String s = stack.getNbt().getString("use");
                        //LOGGER.info("craft() use: " + s);
                        if(s.equals("used")){
                            return ItemStack.EMPTY;
                        }
                        goblinLeatherBagCount++;
                    }
                }
            }
        }
        if(count >= 2 && goblinLeatherBagCount == 1){
            ItemStack stack = new ItemStack(GoblinGroupThings.GOBLIN_LEATHER_BAG);
            NbtList itemList = new NbtList();
            NbtList displayList = new NbtList();
            for(int slot : slots){
                if(inventory.getStack(slot).getItem() == GoblinGroupThings.GOBLIN_LEATHER_BAG) {
                    continue;
                }
                Item item = inventory.getStack(slot).getItem();
                int itemCount = inventory.getStack(slot).getCount();
                String id = Registry.ITEM.getId(item).toString();
                NbtCompound nbt = new NbtCompound();
                nbt.putString("id",id);
                nbt.putInt("count",itemCount);

                if(inventory.getStack(slot).hasNbt()){
                    NbtCompound itemNbt = inventory.getStack(slot).getNbt();
                    nbt.put("itemNbt", itemNbt);
                }
                itemList.add(nbt);
                //判断是item还是block，用于下面的翻译键
                String itemOrBlock = (Block.getBlockFromItem(item) == Blocks.AIR) ? "item" : "block" ;
                //将id拆开用于下面的翻译键
                String id1 = "", id2 = "";
                for(int i = 0; i<id.length(); ++i){
                    if(id.charAt(i) == ':'){
                        id1 = id.substring(0,i);
                        id2 = id.substring(i+1);
                        break;
                    }
                }
                NbtString string = NbtString.of(NbtString.escape(Text.translatable(itemOrBlock + "." + id1 + "." + id2).getString() + " x" + itemCount));
                displayList.add(string);
            }
            stack.setNbt(GoblinLeatherBagItem.usedNbt(itemList, displayList));
            return stack;
        }

        return ItemStack.EMPTY;
    }

    @Override
    public boolean fits(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.USE_GOBLIN_LEATHER_BAG;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        for (int i = 0; i < inventory.size(); ++i) {
            inventory.setStack(i, ItemStack.EMPTY);
        }
        return super.getRemainder(inventory);
    }
}
