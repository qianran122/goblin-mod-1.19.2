package top.qianran;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import static net.fabricmc.fabric.api.registry.VillagerPlantableRegistry.getItems;


public class DemoBlock extends Block implements BlockEntityProvider {
    public DemoBlock(Settings settings) {
        super(settings);
    }



    //将方块实体连接到方块
    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new DemoBlockEntity(pos, state);
    }

    //方块实体刻
    //1.17 添加了静态的刻，然后你就可以实现 Tickable 接口。
    // 对于需要计划刻的方块，你只需要使用 Block 中的 getTicker，
    // 链接回到 Block Entity。参考下面关于刻的一个常见的实现。
    //在你的 Block 类中
  /*  @Override @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(type, GoblinMod.DEMO_BLOCK_ENTITY, (world1, pos, state1, be) -> DemoBlockEntity.tick(world1, pos, state1, be));
    }
    public BlockRenderType getRenderType(BlockState state) {
        // 由于继承了BlockWithEntity，这个默认为INVISIBLE，所以我们需要更改它！
        return BlockRenderType.MODEL;
    }

*/

    //从物品栏（或任何物品栏）中提取和放入
    //我们覆盖方块类中的 `onUse` 行为以从我们的物品栏中加入和提取物品。
    // 注意这也可以对任何 Inventory 实例完成，不仅是我们自己的（例如，也因此可以对箱子方块做同样的事）。
    // 首先我们处理第一个槽位，如果是空的。玩家如果拿着物品，则会将拿着的物品放入。
    // 物品进入第一个槽位，如果是空的，或者进入第二个槽位，如果第一个是空的，或者如果第二个是空的，
    // 我们则会输出与物品栏有关的信息。注意我们将 ItemStack 插入物品/栏时调用 copy()，这样不会随着玩家的 ItemStack 而被破坏。

    @Override
    public ActionResult onUse(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
        if (world.isClient) return ActionResult.SUCCESS;
        Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
        if (!player.getStackInHand(hand).isEmpty()) {
            // Check what is the first open slot and put an item from the player's hand there
            if (blockEntity.getStack(0).isEmpty()) {
                // Put the stack the player is holding into the inventory
                blockEntity.setStack(0, player.getStackInHand(hand).copy());
                // Remove the stack from the player's hand
                player.getStackInHand(hand).setCount(0);
            } else if (blockEntity.getStack(1).isEmpty()) {
                blockEntity.setStack(1, player.getStackInHand(hand).copy());
                player.getStackInHand(hand).setCount(0);
            } else {
                // If the inventory is full we'll print it's contents
                //System.out.println("The first slot holds "
                  //      + blockEntity.getStack(0) + " and the second slot holds " + blockEntity.getStack(1));

                //当玩家不持有物品时，我们将采取相反的行为。
                // 我们将从第二个槽位中取出项目，然后第二个中的第一个为空。如果第一个也是空的，我们将不做任何事情。
                // If the player is not holding anything we'll get give him the items in the block entity one by one
                // Find the first slot that has an item and give it to the player
                if (!blockEntity.getStack(1).isEmpty()) {
                    // Give the player the stack in the inventory
                    player.getInventory().offerOrDrop(blockEntity.getStack(1));
                    // Remove the stack from the inventory
                    blockEntity.removeStack(1);
                } else if (!blockEntity.getStack(0).isEmpty()) {
                    player.getInventory().offerOrDrop(blockEntity.getStack(0));
                    blockEntity.removeStack(0);
                }
            }
        }
        return ActionResult.SUCCESS;
    }




}
