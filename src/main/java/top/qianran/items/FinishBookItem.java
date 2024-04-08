package top.qianran.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.nbt.NbtString;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FinishBookItem extends Item {
    public FinishBookItem(Settings settings) {
        super(settings);
    }



    public static ItemStack writtenBook(String author, String filtered_title, ArrayList<String> pages, boolean resolved, String title) {
        ItemStack stack = new ItemStack(Items.WRITTEN_BOOK);
        NbtCompound baseNbt = new NbtCompound();
        baseNbt.putString("author", author);
        baseNbt.putString("filtered_title", filtered_title);
        NbtList pageList = new NbtList();
        for(String s : pages){
            NbtString string = NbtString.of(s);
            pageList.add(string);
        }
        baseNbt.put("pages", pageList);
        baseNbt.putBoolean("resolved", resolved);
        baseNbt.putString("title", title);
        stack.setNbt(baseNbt);
        return stack;
    }

    public static String bookChar(String s){
        return Text.Serializer.toJson(Text.literal(s));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ArrayList<String> list = new ArrayList<>();
        list.add(bookChar("这是第一页"));
        list.add(bookChar("这是第二页"));
        list.add(bookChar("这是第三页"));
        user.giveItemStack(writtenBook(user.getEntityName(), "My Book",list ,true ,"My First Book"));
        return super.use(world, user, hand);
    }
}
