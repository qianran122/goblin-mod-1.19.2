package top.qianran.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CopyItem extends Item {
    public CopyItem(Settings settings) {
        super(settings);
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
