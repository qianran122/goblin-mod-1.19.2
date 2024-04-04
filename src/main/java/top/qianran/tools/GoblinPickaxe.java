package top.qianran.tools;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GoblinPickaxe extends PickaxeItem {
    public GoblinPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        world.breakBlock(getPos(pos, 1), true, miner);
        world.breakBlock(getPos(pos, -1), true, miner);
        return super.postMine(stack, world, state, pos, miner);
    }

    private BlockPos getPos(BlockPos pos, int y){
        return new BlockPos(pos.getX(), pos.getY()+y, pos.getZ());
    }

}
