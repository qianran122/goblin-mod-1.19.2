package top.qianran.blocks.uiBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import top.qianran.util.ModBlocks;

public class UIBlockEntity extends LootableContainerBlockEntity {

    private final int size = 2;
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(size, ItemStack.EMPTY);

    private int tick = 0;
    public UIBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.UI_BLOCK_ENTITY, blockPos, blockState);
    }
    protected final PropertyDelegate propertyDelegate = new PropertyDelegate() {
        @Override
        public int get(int index) {
            return switch (index) {
                case 0 -> tick;
                default -> 0;
            };
        }

        @Override
        public void set(int index, int value) {
            if(index== 0){
                tick = value;
            }

        }

        @Override
        public int size() {
            return 1;
        }
    };
    public static void tick(UIBlockEntity entity){
        if(!entity.inv.get(0).isEmpty() && entity.inv.get(1).getCount() < entity.inv.get(1).getMaxCount()){
            entity.tick++;
            if(entity.tick == 20 * 4 + 5){
                entity.tick = 0;
                entity.inv.get(0).decrement(1);
                if(entity.inv.get(1).isEmpty()){
                    entity.inv.set(1, new ItemStack(Items.DIAMOND,2));
                } else {
                    entity.inv.get(1).increment(1);
                }
            }
        }
    }

    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return this.inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        this.inv = list;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("ui_block");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new UIBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tick", tick);
        Inventories.writeNbt(nbt, this.inv);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tick = nbt.getInt("tick");
        Inventories.readNbt(nbt, this.inv);
    }
}
