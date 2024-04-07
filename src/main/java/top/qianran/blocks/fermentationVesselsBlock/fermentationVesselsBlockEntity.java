package top.qianran.blocks.fermentationVesselsBlock;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LootableContainerBlockEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import top.qianran.util.ModBlocks;
import top.qianran.util.ModItems;

public class fermentationVesselsBlockEntity extends LootableContainerBlockEntity {

    private int tick = 0;
    private final int size = 4;
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(size, ItemStack.EMPTY);
    public fermentationVesselsBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlocks.FERMENTATION_VESSELS_BLOCK_ENTITY, blockPos, blockState);
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

    //每tick调用一次
    public static void tick(fermentationVesselsBlockEntity entity){
        if( entity.inv.get(0).getItem() == ModItems.GOBLIN_MEAT
         && entity.inv.get(1).getItem() == Items.IRON_INGOT
         && entity.inv.get(2).getItem() == Items.GOLD_INGOT
         && entity.inv.get(3).getCount() < entity.inv.get(3).getMaxCount()) {
            entity.tick++;
            if (entity.tick == 20 * 6) {
                entity.tick = 0;
                entity.inv.get(0).decrement(1);
                entity.inv.get(1).decrement(1);
                entity.inv.get(2).decrement(1);
                if (entity.inv.get(3).isEmpty()) {
                    entity.inv.set(3, new ItemStack(ModItems.GOBLIN_INGOT, 1));
                } else {
                    entity.inv.get(3).increment(1);

                }
            }
        }
    }
    @Override
    protected DefaultedList<ItemStack> getInvStackList() {
        return inv;
    }

    @Override
    protected void setInvStackList(DefaultedList<ItemStack> list) {
        inv = list;
    }

    @Override
    protected Text getContainerName() {
        return Text.translatable("fermentation_vessels");
    }

    @Override
    protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
        return new fermentationVesselsBlockScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putInt("tick", tick);
        Inventories.writeNbt(nbt, inv);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        tick = nbt.getInt("tick");
        Inventories.readNbt(nbt, inv);
    }
}
