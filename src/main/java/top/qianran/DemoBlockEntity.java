package top.qianran;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.Nullable;

import static net.fabricmc.fabric.api.registry.VillagerPlantableRegistry.getItems;

public class DemoBlockEntity extends BlockEntity implements ImplementedInventory, SidedInventory {
    public DemoBlockEntity(BlockPos pos, BlockState state){
        super(GoblinMod.DEMO_BLOCK_ENTITY, pos, state);
    }
    // 储存数字的当前值
    private int number = 7;

    // 序列化方块实体
    /*@Override
    public void writeNbt(NbtCompound nbt) {
        // Save the current value of the number to the tag
        nbt.putInt("number", number);

        super.writeNbt(nbt);
    }*/

    // 反序列化方块实体
    /*@Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        number = nbt.getInt("number");
    }*/

    //将服务器数据同步至客户端
    //对于 1.18 及以上版本，请覆盖 toUpdatePacket 和 toInitialChunkDataNbt
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
    //警告: 需要调用 world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
    //来触发数据的同步，否则客户端不会知道方块实体已经改变。

    //方块实体刻
    //1.17 添加了静态的刻，然后你就可以实现 Tickable 接口。
    // 对于需要计划刻的方块，你只需要使用 Block 中的 getTicker，
    // 链接回到 Block Entity。参考下面关于刻的一个常见的实现。
    //在你的 BlockEntity 类中：

/*    public class DemoBlockEntity extends BlockEntity {
        public DemoBlockEntity(BlockPos pos, BlockState state) {
            super(GoblinMod.DEMO_BLOCK_ENTITY, pos, state);
        }
        public static void tick(World world, BlockPos pos, BlockState state, DemoBlockEntity be) {

        }
    }*/

    // 现在在您的 BlockEntity 中实现ImplementedInventory，
    // 并为其提供存储该物品的 DefaultedList <ItemStack> items 实例。
    // 对于此例，我们将在物品栏中最多存储2件物品：
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    @Override
    public DefaultedList<ItemStack> getItems() {
        return items;
    }

    //我们还需要将物品栏保存到标签并从那里加载。Inventories 具有帮助方法，可以使得这个非常轻松：
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        Inventories.readNbt(nbt, items);
    }
    @Override
    public void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, items);
    }

    //实现 SidedInventory 接口
    //如果你希望有基于与方块不同的面（漏斗或者其他模组）进行交互的不同逻辑，
    // 你可以实现 SidedInventory 接口。如果说你想使得方块不能从上侧插入，可以这样做：
    @Override
    public int[] getAvailableSlots(Direction var1) {
        // Just return an array of all slots
        int[] result = new int[getItems().size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }

        return result;
    }

    @Override
    public boolean canInsert(int slot, ItemStack stack, Direction direction) {
        return direction != Direction.UP;
    }

    @Override
    public boolean canExtract(int slot, ItemStack stack, Direction direction) {
        return true;
    }
}
