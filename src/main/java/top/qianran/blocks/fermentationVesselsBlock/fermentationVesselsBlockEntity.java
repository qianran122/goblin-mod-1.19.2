package top.qianran.blocks.fermentationVesselsBlock;

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
import top.qianran.items.group.GoblinGroupThings;

public class fermentationVesselsBlockEntity extends LootableContainerBlockEntity {

    private int tick = 0;
    private final int size = 10;
    DefaultedList<ItemStack> inv = DefaultedList.ofSize(size, ItemStack.EMPTY);
    public fermentationVesselsBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(GoblinGroupThings.FERMENTATION_VESSELS_BLOCK_ENTITY, blockPos, blockState);
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
        if( entity.inv.get(0).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(1).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(2).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(3).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(4).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(5).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(6).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(7).getItem() == GoblinGroupThings.GOBLIN_MEAT
         && entity.inv.get(8).getItem() == Items.IRON_INGOT
         && entity.inv.get(9).getCount() < entity.inv.get(3).getMaxCount()) {
            entity.tick++;
            if (entity.tick == 20 * 60) {
                entity.tick = 0;
                entity.inv.get(0).decrement(1);
                entity.inv.get(1).decrement(1);
                entity.inv.get(2).decrement(1);
                entity.inv.get(3).decrement(1);
                entity.inv.get(4).decrement(1);
                entity.inv.get(5).decrement(1);
                entity.inv.get(6).decrement(1);
                entity.inv.get(7).decrement(1);
                entity.inv.get(8).decrement(1);

                if (entity.inv.get(9).isEmpty()) {
                    entity.inv.set(9, new ItemStack(GoblinGroupThings.GOBLIN_INGOT, 1));
                } else {
                    entity.inv.get(9).increment(1);

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
        return Text.translatable("block.goblin-mod.fermentation_vessels_block");
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
