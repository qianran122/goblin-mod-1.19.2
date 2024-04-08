package top.qianran.items;

import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class SilkBagItem extends Item {
    public SilkBagItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            NbtList list = (NbtList) stack.getNbt().get("items");
            assert list != null;
            if(!list.isEmpty()){
                for(NbtElement nbtElement : list){
                    NbtCompound nbt = (NbtCompound) nbtElement;
                    String id = nbt.getString("id");
                    int count = nbt.getInt("count");
                    Item item = Registry.ITEM.getOrEmpty(new Identifier(id)).orElseThrow( ()-> new JsonSyntaxException("No such item " + id));
                    user.giveItemStack(new ItemStack(item,count));
                }
            user.getMainHandStack().decrement(1);
            }
        }
        return super.use(world, user, hand);
    }
}
