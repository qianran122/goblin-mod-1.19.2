package top.qianran.blocks.fermentationVesselsBlock;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import top.qianran.items.group.GoblinGroupThings;

public class fermentationVesselsBlockScreenHandler extends ScreenHandler {
    public Inventory inventory;
    private final PropertyDelegate propertyDelegate;
    public fermentationVesselsBlockScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(10), new ArrayPropertyDelegate(1));
    }

    private void setSlot(int index, int x, int y, Item item){
        this.addSlot(new Slot(this.inventory, index, x, y) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.getItem() == item;
            }
        });
    }
    //绘制槽位位置
    public fermentationVesselsBlockScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(GoblinGroupThings.FERMENTATION_VESSELS_BLOCK_SCREEN_HANDLER, syncId);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        this.addProperties(propertyDelegate);
        checkSize(inventory, 3);
        checkDataCount(propertyDelegate, 1);
        setSlot(0, 62, 6, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(1, 85, 11, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(2, 90, 34, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(3, 85, 57, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(4, 62, 62, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(5, 39, 57, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(6, 35, 34, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(7, 39, 11, GoblinGroupThings.GOBLIN_MEAT);
        setSlot(8, 62, 34, Items.IRON_INGOT);
        this.addSlot(new Slot(this.inventory, 9, 136, 35) {
            @Override
            public boolean canInsert(ItemStack stack) {
                return false;
            }
        });
        //绘制玩家物品栏
        int i;
        for(i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for(i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    //获取tick
    public int getTick(){
        return this.propertyDelegate.get(0);
    }

    //处理物品交换
    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < this.inventory.size()) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, this.inventory.size(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return newStack;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
