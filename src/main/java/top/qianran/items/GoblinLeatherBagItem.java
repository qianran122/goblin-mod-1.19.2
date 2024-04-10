package top.qianran.items;

import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.logging.ILogger;

public class GoblinLeatherBagItem extends Item {
    public GoblinLeatherBagItem(Settings settings) {
        super(settings.maxCount(1));
    }

    public static NbtCompound unUsedNbt(){
        NbtCompound baseNbt = new NbtCompound();
        String string = "unused";
        baseNbt.putString("use",string);
        NbtCompound nbt = new NbtCompound();
        NbtList list = new NbtList();
        NbtString nbtString = NbtString.of(NbtString.escape(Text.translatable("Empty").getString()));
        list.add(nbtString);
        nbt.put("Lore", list);
        baseNbt.put("display", nbt);
        return baseNbt;

    }

    public static NbtCompound usedNbt(NbtList itemList, NbtList displayList){
        NbtCompound baseNbt = new NbtCompound();
        String string = "used";
        baseNbt.putString("use",string);
        baseNbt.put("items",itemList);
        NbtCompound nbt = new NbtCompound();
        nbt.put("Lore", displayList);
        baseNbt.put("display", nbt);
        return baseNbt;
    }
    public static final Logger LOGGER = LoggerFactory.getLogger(GoblinLeatherBagItem.class.getName());

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getMainHandStack();
        if(stack.hasNbt()){
            assert stack.getNbt() != null;
            if(stack.getNbt().getString("use").equals("unused")){
                return super.use(world, user, hand);
            }
            NbtList list = (NbtList) stack.getNbt().get("items");
            assert list != null;
            if(!list.isEmpty()){
                for(NbtElement nbtElement : list){
                    NbtCompound nbt = (NbtCompound) nbtElement;
                    String id = nbt.getString("id");
                    int count = nbt.getInt("count");
                    Item item = Registry.ITEM.getOrEmpty(new Identifier(id)).orElseThrow( ()-> new JsonSyntaxException("No such item " + id));
                    ItemStack newStack = new ItemStack(item, count);
                    if(nbt.get("itemNbt") != null ){
                        newStack.setNbt((NbtCompound) nbt.get("itemNbt"));
                    }
                    user.giveItemStack(newStack);
                }
            stack.setNbt(unUsedNbt());
            }
        }
        return super.use(world, user, hand);
    }
}
