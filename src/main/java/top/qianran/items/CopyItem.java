package top.qianran.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.world.storage.NbtScannable;

public class CopyItem extends Item {
    public CopyItem(Settings settings) {
        super(settings);
    }

    public static NbtCompound newCopyItemNbt(int count){
        NbtCompound baseNbt = new NbtCompound();
        baseNbt.putInt("amount", count);
        NbtString string = NbtString.of(NbtString.escape("Number of copies: " + count));
        NbtList list = new NbtList();
        list.add(string);
        NbtCompound nbt = new NbtCompound();
        nbt.put("Lore", list);
        baseNbt.put("display", nbt);
        return baseNbt;
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            return stack.getNbt().get("amount") != null;
        }
        return false;
    }
}
