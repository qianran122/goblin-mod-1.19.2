package top.qianran.tools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import top.qianran.items.group.GoblinGroupThings;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class GoblinAxe extends AxeItem {
    public GoblinAxe(ToolMaterial material, float attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }


    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("item.goblin-mod.goblin_axe.tooltip"));
    }

    //覆盖父类方法，当玩家挖掘方块时触发
    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {

        if(state.getMaterial().equals(Material.WOOD) && stack.getDamage() + 10 <= stack.getMaxDamage()){
            int[] count = {1};
            tryChainMine(world, state, pos, count);
            if(count[0]/3 + stack.getDamage() + 1 >= stack.getMaxDamage()) {
                miner.sendMessage(Text.literal("耐久不足，连锁请求已驳回! (剩余:" + (stack.getMaxDamage()-stack.getDamage()-1) + "/需求:" + count[0]/3 + ")"));
                return super.postMine(stack, world, state, pos, miner);
            }
            //使用数组进行引用传递
            int[] blockCount = {1};
            chainMine(world, state, pos, miner, blockCount);
            if(blockCount[0] >= 64)
                miner.sendMessage(Text.literal("本次连锁方块过多，请注意耐久消耗 \n连锁方块数:"+blockCount[0]+" 耐久消耗:" + (blockCount[0]/3 + 1)));
            //miner.sendMessage(Text.literal("连锁方块数:"+blockCount[0]));
            //miner.sendMessage(Text.literal("耐久上限:"+stack.getMaxDamage()));
            //miner.sendMessage(Text.literal("工具已损失耐久:"+stack.getDamage()));
            //miner.sendMessage(Text.literal("消耗耐久:"+blockCount[0]/3));
            //stack.setDamage(stack.getDamage() + blockCount[0]/3);
            //miner.sendMessage(Text.literal("工具已损失耐久:"+stack.getDamage()));
        } else if(state.getMaterial().equals(Material.WOOD)){
            miner.sendMessage(Text.literal("剩余耐久过低 (剩余:"+(stack.getMaxDamage()-stack.getDamage()-1)+") 连锁请求已驳回!" ));
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    private void tryChainMine(World world, BlockState minedState, BlockPos startPos, int[] count) {
        Stack<BlockPos> stack = new Stack<>();
        Set<BlockPos> visited = new HashSet<>();
        stack.push(startPos);
        visited.add(startPos);

        while (!stack.isEmpty()) {
            BlockPos pos = stack.pop();
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos newPos = pos.add(dx, dy, dz);
                        if (!visited.contains(newPos)) {
                            BlockState newState = world.getBlockState(newPos);
                            if (newState.isOf(minedState.getBlock())) {
                                count[0]++;
                                stack.push(newPos);
                                visited.add(newPos);
                            }
                        }
                    }
                }
            }
        }
    }

    private void chainMine(World world, BlockState minedState, BlockPos pos, LivingEntity miner, int[] blockCount) {
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    BlockPos newPos = pos.add(dx, dy, dz);
                    BlockState newState = world.getBlockState(newPos);
                    if (newState.isOf(minedState.getBlock())) {
                        blockCount[0]++;
                        world.breakBlock(newPos, true, miner);
                        chainMine(world, minedState, newPos, miner, blockCount);
                    }
                }
            }
        }
    }
    private BlockPos getPos(BlockPos pos, int y){
        pos = new BlockPos(pos.getX(), pos.getY() + y, pos.getZ());
        return pos;
    }
}
