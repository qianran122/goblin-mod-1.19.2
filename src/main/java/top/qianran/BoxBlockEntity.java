package top.qianran;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class BoxBlockEntity extends BlockEntity implements ExtendedScreenHandlerFactory, ImplementedInventory {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(16, ItemStack.EMPTY);

    public BoxBlockEntity(BlockPos pos, BlockState state) {
        super(GoblinMod.BOX_BLOCK_ENTITY, pos, state);
    }


    // 从 ImplementedInventory 接口

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;

    }

    // createMenu 会创建 ScreenHandler 自身
    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        //我们将它提供给 screenHandler 作为我们的类实现 Inventory
        //一开始只有服务器(Server)有 Inventory ，这将在 ScreenHandler 中同步到客户端(Cilent)
        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    // getDisplayName 会提供名称，名称通常显示在顶部
    @Override
    public Text getDisplayName() {
        return Text.of(getCachedState().getBlock().getTranslationKey());
    }

    //此方法来自 ExtendedScreenHandlerFactory
    //当它请求客户端(client)打开 screenHandler 时，在服务器(server)上调用此方法
    //您写入 packetByteBuf 的内容将自动以(数据)包的形式传输到客户端
    //并在客户端(client)调用带有 packetByteBuf 参数的 ScreenHandler 构造函数
    //
    //您在此处插入内容的顺序与您需要提取它们的顺序相同。您不需要颠倒顺序！
    @Override
    public void writeScreenOpeningData(ServerPlayerEntity serverPlayerEntity, PacketByteBuf packetByteBuf) {
        //pos 字段是 BlockEntity 的公共字段
        packetByteBuf.writeBlockPos(pos);
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, this.inventory);
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, this.inventory);

    }
/*    // 这些方法来自 NamedScreenHandlerFactory 接口
    // createMenu 会创建 ScreenHandler 自身
    // getDisplayName 会提供名称，名称通常显示在顶部

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        // 因为我们的类实现 Inventory，所以将*这个*提供给 ScreenHandler
        // 一开始只有服务器拥有物品栏，然后在 ScreenHandler 中同步给客户端
        return new BoxScreenHandler(syncId, playerInventory, this);
    }

    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
        // 对于1.19之前的版本，请使用：
        // return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }*/
    

}