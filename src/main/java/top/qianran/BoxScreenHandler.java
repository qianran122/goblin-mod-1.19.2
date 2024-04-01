package top.qianran;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.network.PacketByteBuf;

public class BoxScreenHandler extends ScreenHandler {

    //我们保存从服务器获得的 blockPos 并为其提供一个 getter，以便 BoxScreen 可以读取该信息
    private BlockPos pos;
    private final Inventory inventory;

    // 服务器想要客户端开启 screenHandler 时，客户端调用这个构造器。
    // 如有空的物品栏，客户端会调用其他构造器，screenHandler 将会自动
    // 在客户端将空白物品栏同步给物品栏。
    public BoxScreenHandler(int syncId, PlayerInventory playerInventory, PacketByteBuf buf) {
        this(syncId, playerInventory, new SimpleInventory(16));
        pos = buf.readBlockPos();
    }

    // 此构造函数从服务器上的 BlockEntity 调用，服务器知道容器的库存(你可以将它理解为物品栏)，
    // 因此可以直接将其作为参数提供。 然后，此库存(物品栏)将同步到客户端。
    public BoxScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory) {
        super(GoblinMod.BOX_SCREEN_HANDLER, syncId);
        checkSize(inventory, 16);
        this.inventory = inventory;
        // 玩家开启时，一些物品栏有自定义的逻辑。
        inventory.onOpen(playerInventory.player);

        // 这会将槽位放置在 3×3 网格的正确位置中。这些槽位在客户端和服务器中都存在！
        // 但是这不会渲染槽位的背景，这是 Screens 类的工作
        int m;
        int l;
        //Our inventory
        for (m = 0; m < 4; ++m) {
            for (l = 0; l < 4; ++l) {
                this.addSlot(new Slot(inventory, l + m * 4, 62-9 + l * 18, 17-9 + m * 18));
            }
        }
        // 玩家物品栏
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }
        // 玩家快捷栏
        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
        //想一想，为什么我们在这里使用 BlockPos.ORIGIN？
        //This is because the packetByteBuf with our blockPosition is only availible on the Client, so we need a placeholder
        //value here. This is not a problem however, as the Server version of the ScreenHandler does not really need this
        //information.
        //(机翻警告)这是因为带有我们 blockPosition 的 packetByteBuf 仅在 Client 上可用，所以我们这里需要一个占位符值。 然而，这不是问题，
        //因为 ScreenHandler 的服务器版本并不真正需要此信息。
        pos = BlockPos.ORIGIN;
    }
    //这个 getter 将被我们的 Screen 类使用
    public BlockPos getPos() {
        return pos;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    // Shift + 玩家物品栏槽位
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
}